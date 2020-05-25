package com.example.navigationarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.Frame;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import uk.co.appoly.arcorelocation.LocationMarker;
import uk.co.appoly.arcorelocation.LocationScene;
import uk.co.appoly.arcorelocation.rendering.LocationNode;
import uk.co.appoly.arcorelocation.rendering.LocationNodeRender;
import uk.co.appoly.arcorelocation.utils.ARLocationPermissionHelper;

public class MainActivity extends AppCompatActivity {

    private boolean installRequested;
    private boolean hasFinishedLoading = false;

    private Snackbar loadingMessageSnackbar = null;

    private ArSceneView arSceneView;
    private LocationScene locationScene;

    Intent intent2;

    // Renderables for this example
    private ModelRenderable andyRenderable;
    private ViewRenderable exampleLayoutRenderable;
    private ViewRenderable exampleLayoutRenderable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arSceneView = findViewById(R.id.ar_scene_view);

        TextView tx1 = findViewById(R.id.textView5);
        TextView tx2 = findViewById(R.id.textView6);
        TextView tx3 = findViewById(R.id.textView8);
        Button bt = findViewById(R.id.button2);

        // Build a renderable from a 2D View.
        CompletableFuture<ViewRenderable> exampleLayout =
                ViewRenderable.builder()
                        .setView(this, R.layout.example_layout)
                        .build();

        CompletableFuture<ViewRenderable> exampleLayout2 =
                ViewRenderable.builder()
                        .setView(this, R.layout.example_layout)
                        .build();

        CompletableFuture<ModelRenderable> andy = ModelRenderable.builder()
                .setSource(this, Uri.parse("andy.sfb"))
                .build();

      CompletableFuture.allOf(
                exampleLayout)
                .handle(
                        (notUsed, throwable) -> {
                            // When you build a Renderable, Sceneform loads its resources in the background while
                            // returning a CompletableFuture. Call handle(), thenAccept(), or check isDone()
                            // before calling get().

                            if (throwable != null) {
                                DemoUtils.displayError(this, "Unable to load renderables", throwable);
                                return null;
                            }

                            try {
                                exampleLayoutRenderable = exampleLayout.get();

                               // andyRenderable = andy.get();
                                hasFinishedLoading = true;

                            } catch (InterruptedException | ExecutionException ex) {
                                DemoUtils.displayError(this, "Unable to load renderables", ex);
                            }

                            return null;
                        });

        CompletableFuture.allOf(
                exampleLayout2)
                .handle(
                        (notUsed, throwable) -> {
                            // When you build a Renderable, Sceneform loads its resources in the background while
                            // returning a CompletableFuture. Call handle(), thenAccept(), or check isDone()
                            // before calling get().

                            if (throwable != null) {
                                DemoUtils.displayError(this, "Unable to load renderables", throwable);
                                return null;
                            }

                            try {
                                exampleLayoutRenderable2 = exampleLayout2.get();

                                // andyRenderable = andy.get();
                                hasFinishedLoading = true;

                            } catch (InterruptedException | ExecutionException ex) {
                                DemoUtils.displayError(this, "Unable to load renderables", ex);
                            }

                            return null;
                        });

        // Set an update listener on the Scene that will hide the loading message once a Plane is
        // detected.
        arSceneView.getScene().addOnUpdateListener(
                frameTime -> {
                    if (!hasFinishedLoading) {
                        return;
                    }

                    if (locationScene == null) {
                        // If our locationScene object hasn't been setup yet, this is a good time to do it
                        // We know that here, the AR components have been initiated.
                        locationScene = new LocationScene(this, arSceneView);

                        // Now lets create our location markers.
                        // First, a layout
                        LocationMarker layoutLocationMarker = new LocationMarker(
                                37.708107, 55.754296,
                                getExampleView()
                        );

                        LocationMarker layoutLocationMarker2 = new LocationMarker(
                                37.867772, 55.744583,
                                getExampleView2()
                        );
                        locationScene.mLocationMarkers.add(layoutLocationMarker);
                        locationScene.mLocationMarkers.add(layoutLocationMarker2);

                     /*   for (LocationMarker lm :locationScene.mLocationMarkers)
                        {
                            lm.setRenderEvent(anchornode -> {

                                View eView = exampleLayoutRenderable.getView();

                                TextView distanceTextView = eView.findViewById(R.id.textView2);
                                TextView coordTextView = eView.findViewById(R.id.textView9);

                                distanceTextView.setText(anchornode.getDistance() + "M");
                                coordTextView.setText(lm.latitude + " " + lm.longitude);
                                //включение и отключение метки,если расстояние меньше заданного. работает.
                                if(lm.anchorNode.getDistance() > 350)
                                {
                                    lm.node.setEnabled(false);
                                    tx2.setText("out of range");
                                }
                                else
                                {
                                    tx2.setText(anchornode.getDistance() + "M");
                                    // tx3.setText("out of vision");
                                    lm.node.setEnabled(true);
                                }

                            });
                        }*/

                        layoutLocationMarker.setRenderEvent(anchornode -> {

                            View eView = exampleLayoutRenderable.getView();

                            TextView distanceTextView = eView.findViewById(R.id.textView2);
                            TextView coordTextView = eView.findViewById(R.id.textView9);

                            distanceTextView.setText(anchornode.getDistance() + "M");
                            coordTextView.setText(layoutLocationMarker.latitude + " " + layoutLocationMarker.longitude);
                            locationScene.mLocationMarkers.get(0).setScaleModifier(0.4f);
                        //    locationScene.mLocationMarkers.get(1).setScaleModifier(0.4f);
                            locationScene.mLocationMarkers.get(0).setScalingMode(LocationMarker.ScalingMode.GRADUAL_TO_MAX_RENDER_DISTANCE);
                            //включение и отключение метки,если расстояние меньше заданного. работает.
                            if(locationScene.mLocationMarkers.get(0).anchorNode.getDistance() > 350)
                            {
                                locationScene.mLocationMarkers.get(0).node.setEnabled(false);
                          //      tx2.setText("out of range");
                            }
                            else
                            {
                          //      tx2.setText(anchornode.getDistance() + "M");
                                // tx3.setText("out of vision");
                                locationScene.mLocationMarkers.get(0).node.setEnabled(true);
                            }

                        });
                        // Adding the marker

                        // An example "onRender" event, called every frame
                        // Updates the layout with the markers distance
                        layoutLocationMarker2.setRenderEvent(anchornode -> {

                            View eView2 = exampleLayoutRenderable2.getView();

                            TextView distanceTextView = eView2.findViewById(R.id.textView2);
                            TextView coordTextView = eView2.findViewById(R.id.textView9);

                            distanceTextView.setText(anchornode.getDistance() + "M");
                            coordTextView.setText(layoutLocationMarker2.latitude + " " + layoutLocationMarker2.longitude);
                            //включение и отключение метки,если расстояние меньше заданного. работает.
                            if(locationScene.mLocationMarkers.get(1).anchorNode.getDistance() > 350)
                            {
                                locationScene.mLocationMarkers.get(1).node.setEnabled(false);
                          //      tx3.setText("out of range");
                            }
                            else
                            {
                          //      tx3.setText(anchornode.getDistance() + "M");
                                // tx3.setText("out of vision");
                                locationScene.mLocationMarkers.get(1).node.setEnabled(true);
                            }

                        });

                        // Adding the marker
                        // An example "onRender" event, called every frame
                    /*    // Updates the layout with the markers distance

                     //   tx2.setText(layoutLocationMarker.latitude + " " + layoutLocationMarker.longitude);

                        // Adding a simple location marker of a 3D model
                               locationScene.mLocationMarkers.add(
                                        new LocationMarker(
                                                55.756100, 37.703365,
                                                getAndy()));
                            }

                            //Frame process
                            Frame frame = arSceneView.getArFrame();
                            if (frame == null) {
                                return;
                            }

                            if (frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
                                return;
                            }

                            if (locationScene != null) {
                                try {


                                    tx1.setText(locationScene.deviceLocation.currentBestLocation.getLatitude() + " " +
                                            locationScene.deviceLocation.currentBestLocation.getLongitude());
                                }
                                catch (NullPointerException ex)
                                {
                                    tx1.setText("No Current location");
                                    // tx2.setText("No Current location");
                                }
                                locationScene.processFrame(frame);
                            }

                            if (loadingMessageSnackbar != null) {
                                for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                                    if (plane.getTrackingState() == TrackingState.TRACKING) {
                                      //  hideLoadingMessage();
                                    }
                                }
                            }

                        });
        );
         arSceneView
                .getScene()
                .setOnUpdateListener(
               // .addOnUpdateListener(this::OnUpdateFrame);
                        frameTime -> {
                            if (!hasFinishedLoading) {
                                return;
                            }

                            if (locationScene == null) {
                                // If our locationScene object hasn't been setup yet, this is a good time to do it
                                // We know that here, the AR components have been initiated.
                                locationScene = new LocationScene(this, arSceneView);

                                // Now lets create our location markers.
                                // First, a layout
                                LocationMarker layoutLocationMarker = new LocationMarker(
                                        37.703365, 55.756100,
                                        getExampleView()
                                );

                                // An example "onRender" event, called every frame
                                // Updates the layout with the markers distance
                                layoutLocationMarker.setRenderEvent(node -> {
                                    View eView = exampleLayoutRenderable.getView();
                                    TextView distanceTextView = eView.findViewById(R.id.textView2);
                                    distanceTextView.setText(node.getDistance() + "M");
                                });
                                // Adding the marker
                                locationScene.mLocationMarkers.add(layoutLocationMarker);

                                tx2.setText(layoutLocationMarker.latitude + " " + layoutLocationMarker.longitude);


                                // Adding a simple location marker of a 3D model
                              /* locationScene.mLocationMarkers.add(
                                        new LocationMarker(
                                                55.756100, 37.703365,
                                                getAndy()));*/
                            }

                            //Frame process
                            Frame frame = arSceneView.getArFrame();
                            if (frame == null) {
                                return;
                            }

                            if (frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
                                return;
                            }
                            //locationScene.mLocationMarkers.get(0)

                            if (locationScene != null) {

                                try {

                                 /*   if(!locationScene.mLocationMarkers.isEmpty())
                                    {

                                        //tx3.setText("xd");
                                        if(locationScene.mLocationMarkers.get(1).anchorNode.getDistance() > 100)
                                        {
                                           locationScene.mLocationMarkers.get(0).node.setEnabled(false);
                                           // tx3.setText(locationScene.mLocationMarkers.);
                                        }
                                        else
                                        {
                                            tx3.setText("out of vision");
                                        }
                                    }*/

                            //        tx1.setText(locationScene.deviceLocation.currentBestLocation.getLatitude() + " " +
                              //              locationScene.deviceLocation.currentBestLocation.getLongitude());

                                }
                                catch (NullPointerException ex)
                                {
                           //         tx1.setText("No Current location");
                                    // tx2.setText("No Current location");
                                }
                                locationScene.processFrame(frame);
                            }

                            if (loadingMessageSnackbar != null) {
                                for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                                    if (plane.getTrackingState() == TrackingState.TRACKING) {
                                      //  hideLoadingMessage();
                                    }
                                }
                            }

                        });
        bt.setOnClickListener(view -> {
            locationScene.mLocationMarkers.get(1).latitude = 55.743514;
            locationScene.mLocationMarkers.get(1).longitude =37.869371;
        });
    }

  /*  private void OnUpdateFrame(FrameTime frameTime)
    {
        if (!hasFinishedLoading) {
            return;
        }

        if (locationScene == null) {
            // If our locationScene object hasn't been setup yet, this is a good time to do it
            // We know that here, the AR components have been initiated.
            locationScene = new LocationScene(this, arSceneView);

            // Now lets create our location markers.
            // First, a layout
            LocationMarker layoutLocationMarker = new LocationMarker(
                    37.703365, 55.756100,
                    getExampleView()
            );

            // An example "onRender" event, called every frame
            // Updates the layout with the markers distance
            layoutLocationMarker.setRenderEvent(new LocationNodeRender() {
                @Override
                public void render(LocationNode node) {
                    View eView = exampleLayoutRenderable.getView();
                    TextView distanceTextView = eView.findViewById(R.id.textView2);
                    distanceTextView.setText(node.getDistance() + "M");
                }
            });
            // Adding the marker
            locationScene.mLocationMarkers.add(layoutLocationMarker);

            // Adding a simple location marker of a 3D model
                             /*   locationScene.mLocationMarkers.add(
                                        new LocationMarker(
                                                55.756100, 37.703365,
                                                getAndy()));

        }
        Frame frame = arSceneView.getArFrame();
        if (frame == null) {
            return;
        }

        if (frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
            return;
        }

        if (locationScene != null) {
            locationScene.processFrame(frame);
        }

        if (loadingMessageSnackbar != null) {
            for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                if (plane.getTrackingState() == TrackingState.TRACKING) {
                    //  hideLoadingMessage();
                }
            }
        }
    } */

    /**
     * Make sure we call locationScene.resume();
     */
    @Override
    protected void onResume() {
        super.onResume();

        if (locationScene != null) {
            locationScene.resume();
        }

        if (arSceneView.getSession() == null) {
            // If the session wasn't created yet, don't resume rendering.
            // This can happen if ARCore needs to be updated or permissions are not granted yet.
            try {
                Session session = DemoUtils.createArSession(this, installRequested);
                if (session == null) {
                    installRequested = ARLocationPermissionHelper.hasPermission(this);
                    return;
                } else {
                    arSceneView.setupSession(session);
                }
            } catch (UnavailableException e) {
                DemoUtils.handleSessionException(this, e);
            }
        }

        try {
            arSceneView.resume();
        } catch (CameraNotAvailableException ex) {
            DemoUtils.displayError(this, "Unable to get camera", ex);
            finish();
            return;
        }

        if (arSceneView.getSession() != null) {
           // showLoadingMessage();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        arSceneView.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
         intent2 = new Intent(MainActivity.this, StartActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent2);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private Node getExampleView() {
        Node base = new Node();
        base.setRenderable(exampleLayoutRenderable);
        Context c = this;
        // Add  listeners etc here
        View eView = exampleLayoutRenderable.getView();
        eView.setOnTouchListener((v, event) -> {
            Toast.makeText(
                    c, "Location marker touched.", Toast.LENGTH_LONG)
                    .show();

            Intent intent = new Intent(MainActivity.this, QuestActivity.class);
            startActivity(intent);
            //добавить отправку сообщения.
            return false;
        });
        return base;
    }

    private Node getExampleView2() {
        Node base = new Node();
        base.setRenderable(exampleLayoutRenderable2);
        Context c = this;
        // Add  listeners etc here
        View eView = exampleLayoutRenderable2.getView();
        eView.setOnTouchListener((v, event) -> {
            Toast.makeText(
                    c, "Location marker touched.", Toast.LENGTH_LONG)
                    .show();

            Intent intent = new Intent(MainActivity.this, QuestActivity.class);
            startActivity(intent);
            //добавить отправку сообщения.
            return false;
        });
        return base;
    }

}
