package com.navigation.offline.map.guide.mapoffline;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.ArchiveFileFactory;
import org.osmdroid.tileprovider.modules.IArchiveFile;
import org.osmdroid.tileprovider.modules.OfflineTileProvider;
import org.osmdroid.tileprovider.tilesource.FileBasedTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.util.Set;

import static com.navigation.offline.map.guide.mapoffline.R.id.map;

// My work
// 3 commit
// from the website
public class MainActivity extends Activity {

    //12345
    private MyLocationNewOverlay locationOverlay;
    private CompassOverlay compassOverlay;
    private RotationGestureOverlay rotationGestureOverlay;
    private ScaleBarOverlay scaleBarOverlay;
    //567890

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        //important! set your user agent to prevent getting banned from the osm servers  
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_main);

        //from the internet
        MapView mapView = (MapView) findViewById(map);
        //from the home

        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
      //  mapView.setTilesScaledToDpi(true);

        compassOverlay = new CompassOverlay(ctx, new InternalCompassOrientationProvider(ctx), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(this.compassOverlay);

        IMapController mapController = mapView.getController();
        mapController.setZoom(12);
        GeoPoint startPoint = new GeoPoint(48.8566, 2.3522);
        mapController.setCenter(startPoint);

        scaleBarOverlay = new ScaleBarOverlay(mapView);
        scaleBarOverlay.setAlignRight(true);
        scaleBarOverlay.setAlignBottom(true);
        mapView.getOverlays().add(scaleBarOverlay);



        //first we'll look at the default location for tiles that we support
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/osmdroid/");
        if (f.exists()){

            File[] list = f.listFiles();
            for (int i=0; i < list.length; i++){
                if (list[i].isDirectory())
                    continue;
                String name = list[i].getName().toLowerCase();
                if (!name.contains("."))
                    continue; //skip files without an extension
                name=name.substring(name.lastIndexOf(".")+1);
                if (name.length() ==0)
                    continue;
                if (ArchiveFileFactory.isFileExtensionRegistered(name)) {
                    try {

                        //ok found a file we support and have a driver for the format, for this demo, we'll just use the first one

                        //create the offline tile provider, it will only do offline file archives
                        //again using the first file
                        OfflineTileProvider tileProvider= new OfflineTileProvider(new SimpleRegisterReceiver(this), new File[]{list[i]});
                        //tell osmdroid to use that provider instead of the default rig which is (asserts, cache, files/archives, online
                        mapView.setTileProvider(tileProvider);

                        //this bit enables us to find out what tiles sources are available. note, that this action may take some time to run
                        //and should be ran asynchronously. we've put it inline for simplicity

                        String source="";
                        IArchiveFile[] archives= tileProvider.getArchives();
                        if (archives.length > 0){
                            //cheating a bit here, get the first archive file and ask for the tile sources names it contains
                            Set<String> tileSources = archives[0].getTileSources();
                            //presumably, this would be a great place to tell your users which tiles sources are available
                            if (!tileSources.isEmpty()) {
                                //ok good, we found at least one tile source, create a basic file based tile source using that name
                                //and set it. If we don't set it, osmdroid will attempt to use the default source, which is "MAPNIK",
                                //which probably won't match your offline tile source, unless it's MAPNIK
                                source = tileSources.iterator().next();
                                mapView.setTileSource(FileBasedTileSource.getSource(source));
                            }
                            else{
                                mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                            }

                        } else
                            mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

                        Toast.makeText(this, "Using " + list[i].getAbsolutePath() + " " + source, Toast.LENGTH_LONG).show();
                        mapView.invalidate();
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            Toast.makeText(this, f.getAbsolutePath() +  " did not have any files I can open! Try using MOBAC", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, f.getAbsolutePath() + " dir not found!", Toast.LENGTH_LONG).show();
        }




//        locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx), mapView);
//        locationOverlay.enableMyLocation();
//        mapView.getOverlays().add(locationOverlay);



//        rotationGestureOverlay = new RotationGestureOverlay(mapView);
//        rotationGestureOverlay.setEnabled(true);
//        mapView.setMultiTouchControls(true);
//        mapView.getOverlays().add(rotationGestureOverlay);

//        //your items
//        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
//        items.add(new OverlayItem("Title", "Description", new GeoPoint(53.9045d, 27.5615d))); // Lat/Lon decimal degrees
//        //the overlay
//        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(ctx, items,
//                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
//                    @Override
//                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
//                        //do something
//                        return true;
//                    }
//                    @Override
//                    public boolean onItemLongPress(final int index, final OverlayItem item) {
//                        return false;
//                    }
//                });
//        mOverlay.setFocusItemsOnTap(true);
//        mapView.getOverlays().add(mOverlay);
    }

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use 
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }
}
