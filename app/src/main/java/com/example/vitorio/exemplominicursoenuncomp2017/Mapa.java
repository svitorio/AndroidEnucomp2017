package com.example.vitorio.exemplominicursoenuncomp2017;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vitorio.exemplominicursoenuncomp2017.Banco.DatabaseControl;
import com.example.vitorio.exemplominicursoenuncomp2017.modelo.Ponto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Toast.makeText(getApplicationContext(), "Provider: " + provider, Toast.LENGTH_LONG).show();

            mMap = googleMap;

            mMap.setOnMapClickListener(this);

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {

            Log.e("TAG", "Error", ex);
        }
        mMap = googleMap;
        final Location laslocation;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        laslocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Toast.makeText(getApplicationContext(),"Lati:"+laslocation.getLatitude()+"\nLong:: "+laslocation.getLongitude(),Toast.LENGTH_SHORT).show();

        LatLng posicao=new LatLng(laslocation.getLatitude(),laslocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicao));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicao,(float)17.5));

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-2.9082825, -41.7541362);

        DatabaseControl dc = new DatabaseControl(this);
        ArrayList<Ponto> lista = new ArrayList<Ponto>();
        lista = dc.carregaDados();


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Casa Stark"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        for(int i=0;i<lista.size();i++) {
            LatLng sydney = new LatLng(lista.get(i).getLatitude(),lista.get(i).getLongitude());


            MarkerOptions marker = new MarkerOptions();
            marker.position(sydney);
            marker.title(lista.get(i).getNome());
            mMap.addMarker(marker);


            //mMap.addMarker(new MarkerOptions().position(new LatLng(lista.get(i).getLatitude(),lista.get(i).getLongitude())).title(lista.get(i).getNome()));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
    public void onMapClick(LatLng latLng) {

        // Toast.makeText(getContext(),"Click no local do Evento!",
        //  Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Latitude: "+latLng.latitude+"\nLongitude"+latLng.longitude,Toast.LENGTH_LONG).show();
        Log.i("Aaa","Latitude: "+latLng.latitude+"\nLongitude"+latLng.longitude);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
