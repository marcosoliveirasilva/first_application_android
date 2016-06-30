package br.edu.ifba.mobile.controledevendas;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import br.edu.ifba.mobile.controledevendas.bd.FachadaBD;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBDCompras;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaProdutos;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoInformacao;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaClientes;

public class ControleDeVendasActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_de_vendas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        paginador.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(paginador);


        paginador.addOnPageChangeListener(this);

        FachadaBD.criarInstancia(this.getApplicationContext());
        FachadaBDCompras.criarInstancia(this.getApplicationContext());

    }


      @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==1)//position é o numero da aba da tela pois existe tela 0,1,2
        {
            FragmentoListaClientes.getInstancia().atualizar();
        }
        else if(position == 2)
        {
            FragmentoListaProdutos.getInstancia().atualizar();//
        }



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position)
            {
                case 0:
                    frag = FragmentoInformacao.getInstancia();

                    break;
                case 1 :
                    frag= FragmentoListaClientes.getInstancia();

                    break;
                case 2 :
                    frag= FragmentoListaProdutos.getInstancia();

                    break;
            }

            return frag;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informações"; //criando as abas
                case 1:
                    return "Clientes";
                case 2:
                    return "Compras";
            }
            return null;
        }
    }
}
