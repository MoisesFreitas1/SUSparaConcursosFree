package susparaconcursosfree.mxtechnologies.com.susparaconcursosfree;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager FM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FM = getFragmentManager();
        FM.beginTransaction().replace(R.id.content_principal, new bemvindo()).commit();
        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=susparaconcursosfree.mxtechnologies.com.susparaconcursosfree");
                share.setType("text/plain");
                startActivity(share);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_introsus) {
            FM.beginTransaction().replace(R.id.content_principal, new IntroSUSActivity()).commit();
        } else if (id == R.id.nav_setpriv) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_pridou) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_priorg) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_promo) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_prot) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_rec) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_hist) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_crtscl) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_sus10) {
            FM.beginTransaction().replace(R.id.content_principal, new SUS20()).commit();
        } else if (id == R.id.nav_sus20) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_sus30) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        } else if (id == R.id.nav_face) {
            Uri uri = Uri.parse("https://m.facebook.com/Prepara-X-Cursos-961279907257312/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.nav_email) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","preparaxcursos@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá,\n");
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
        }else if (id == R.id.nav_lei8080) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        }else if (id == R.id.nav_cf194200) {
            FM.beginTransaction().replace(R.id.content_principal, new CompleteVersion()).commit();
        }else if (id == R.id.nav_site) {
            Uri uri = Uri.parse("http://www.preparaxcursos.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else if (id == R.id.nav_upgrade) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=susparaconcursos.mxtechnologies.com.susparaconcursos");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
