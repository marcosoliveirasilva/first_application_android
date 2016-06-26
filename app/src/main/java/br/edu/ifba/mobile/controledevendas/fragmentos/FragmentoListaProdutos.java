package br.edu.ifba.mobile.controledevendas.fragmentos;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.CadastroComprasActivity;
import br.edu.ifba.mobile.controledevendas.ControleDeVendasActivity;
import br.edu.ifba.mobile.controledevendas.R;
import br.edu.ifba.mobile.controledevendas.bd.Compras;
import br.edu.ifba.mobile.controledevendas.tarefas.ListagemProdutos;
import br.edu.ifba.mobile.controledevendas.tarefas.RemocaoCompra;

/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoListaProdutos extends Fragment {


    private static FragmentoListaProdutos instancia = null;

    public static FragmentoListaProdutos getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoListaProdutos();

        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_lista_produtos, vgrupo, false);
        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador)
    {
        super.onCreateOptionsMenu(menu, inflador);

        inflador.inflate(R.menu.menu_controle_notas, menu);//inflar é traduzir o codigo do xml para o visual

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        long id = item.getItemId(); // cada menu tem seu id

        if(id != AdapterView.INVALID_ROW_ID)// verificando se o id é valido
        {
            if (id == R.id.cadastro_remover)
            {
                ControleDeVendasActivity atualizacao = new ControleDeVendasActivity();
                RemocaoCompra remocao = new RemocaoCompra(this.getContext(),this.getCompraSelecionada());
                remocao.execute();
                atualizar();
            }else if (id == R.id.cadastro_inserir){
                if (FragmentoListaClientes.getInstancia().getSelecionouCliente() == false)
                    Toast.makeText(getContext(),"Selecione um cliente na tela anterior para inserir uma compra!  ", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getContext(), CadastroComprasActivity.class);
                    startActivity(intent);
                }
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar()

    {
        lista = (ListView) tela.findViewById(R.id.listaCompras);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar() {
        ListagemProdutos listagem = new ListagemProdutos(this.getContext(), lista);
        listagem.execute();
    }

    public Compras getCompraSelecionada(){
        Compras compras = new Compras();

        int posicao= lista.getCheckedItemPosition();// saber qual cliente está selecionada

        if(posicao!= ListView.INVALID_POSITION){
            compras = (Compras) lista.getItemAtPosition(posicao);//converendo para  o tipo cliente
        }

        return compras;
    }
}

