package com.borjalapa.appexamen1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class tabs extends AppCompatActivity {
Toolbar tbMiToolBar;
Drawer nDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Bundle extras = getIntent().getExtras();
        String usuario = getIntent().getExtras().getString("Usuario");
        String fecha = getIntent().getExtras().getString("Fecha de Nacimiento");
        String email = getIntent().getExtras().getString("Email");

        tbMiToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(tbMiToolBar);

        new DrawerBuilder().withActivity(this).build();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_reproductor)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.material_drawer_selected)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(usuario)
                                .withEmail(email)
                                .withIcon(R.drawable.ic_user)
                )
                .build();

        nDrawer = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withToolbar(tbMiToolBar)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withAccountHeader(header)
                .withSelectedItem(2)
                .withSliderBackgroundColor(getResources().getColor(R.color.material_drawer_primary_light))
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withIdentifier(1)
                                .withName("Lista de la compra")
                                .withIcon(android.R.drawable.btn_star_big_on),
                        new PrimaryDrawerItem()
                                .withIdentifier(2)
                                .withName("Lottie"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withIdentifier(3)
                                .withName("Video y Audio"),
                        new SecondaryDrawerItem()
                                .withIdentifier(4)
                                .withName("Cerrar Menu")
                )

                .withOnDrawerItemClickListener(
                        new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                switch ((int) drawerItem.getIdentifier()) {
                                    case 1: {
                                        Toast.makeText(tabs.this, "Opcion 1 pulsada", Toast.LENGTH_SHORT).show();
                                        Intent calculadora = new Intent();
                                        calculadora.setAction(Intent.ACTION_SEND);
                                        calculadora.putExtra(Intent.EXTRA_TEXT,0.0);
                                        calculadora.setType("text/plain");
                                        Intent shareIntent = Intent.createChooser(calculadora, null);
                                        startActivityForResult(shareIntent, 0);
                                        break;
                                    }
                                    case 2: {
                                        Toast.makeText(tabs.this, "Opcion 2 pulsada", Toast.LENGTH_SHORT).show();
                                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.catata.juegolottie");
                                        if (launchIntent != null) {
                                            startActivity(launchIntent);//null pointer check in case package name was not found
                                        }
                                        break;
                                    }
                                    case 3:
                                        Toast.makeText(tabs.this, "Opcion 3 pulsada", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 4: {
                                        Toast.makeText(tabs.this, "Opcion 4 pulsada", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    default: {
                                        break;
                                    }
                                }
                                nDrawer.closeDrawer();
                                return true;
                            }
                        })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (item.getItemId()){
            case R.id.salir:
                builder.setTitle("Cerrar sesión")
                        .setMessage("Pulsa si para cerrar sesión")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog dialog = builder.show();

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    }




