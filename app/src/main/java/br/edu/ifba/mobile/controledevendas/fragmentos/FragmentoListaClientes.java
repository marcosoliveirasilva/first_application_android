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

import br.edu.ifba.mobile.controledevendas.R;
import br.edu.ifba.mobile.controledevendas.CadastroClientesActivity;
import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.tarefas.ListagemClientes;
import br.edu.ifba.mobile.controledevendas.tarefas.RemocaoCliente;

/**
 * Created by alunoifba on 20/05/2016.
 */
public class FragmentoListaClientes extends Fragment {

    private static FragmentoListaClientes instancia = null; //transformando

    public static FragmentoListaClientes getInstancia() {
        if (instancia == null) //para criar apenas uma intancia para o projeto  int
        {
            instancia = new FragmentoListaClientes();
        }
        return instancia;

    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_lista_clientes, vgrupo, false);
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
                RemocaoCliente remocao = new RemocaoCliente(this.getContext(),this.getClienteSelecionado());
                remocao.execute();
                atualizar();
            }else if (id == R.id.cadastro_inserir){
                    Intent intent = new Intent(getContext(), CadastroClientesActivity.class);
                    startActivity(intent);

            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar()

    {
        lista = (ListView) tela.findViewById(R.id.listaClientes);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar() {
        ListagemClientes listagem = new ListagemClientes(this.getContext(), lista);
        listagem.execute();
    }

    public Cliente getClienteSelecionado(){
        Cliente cliente = new Cliente();

        int posicao= lista.getCheckedItemPosition();// saber qual cliente está selecionada

        if(posicao!= ListView.INVALID_POSITION){
            cliente = (Cliente) lista.getItemAtPosition(posicao);//converendo para  o tipo cliente
        }

        return cliente;
    }

    public boolean getSelecionouCliente(){
        boolean selecionou = false;

        int posicao= lista.getCheckedItemPosition();// saber qual cliente está selecionada

        if(posicao!= ListView.INVALID_POSITION){
            selecionou = true;
        }

        return selecionou;
    }

}
