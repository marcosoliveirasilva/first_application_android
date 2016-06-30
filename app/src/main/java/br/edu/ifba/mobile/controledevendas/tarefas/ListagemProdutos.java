package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.bd.Compras;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBDCompras;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaClientes;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class ListagemProdutos extends  AsyncTask<Void, Void, List<Compras>> {

    private  Context contexto = null;
    private ListView listaCompras = null;

    public ListagemProdutos(Context contexto, ListView listaCompras)
    {
        this.contexto = contexto;
        this.listaCompras = listaCompras;

    }

    @Override
    protected List<Compras> doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano
        Cliente cliente= FragmentoListaClientes.getInstancia().getClienteSelecionado();

         List<Compras> produtosListados = FachadaBDCompras.getInstancia().listarCompras(""+cliente.getCodigo());

        return produtosListados;
    }

    @Override
    protected void onPostExecute(List<Compras> produtosListados)//depois da execucao// somente aqui que deve exibir a mensagem
    {
        if(produtosListados.isEmpty()){
            if (FragmentoListaClientes.getInstancia().getSelecionouCliente() == false) {
                listaCompras.setAdapter(null);
                Toast.makeText(contexto, "Selecione um cliente na tela anterior!  ", Toast.LENGTH_LONG).show();
            }
            else {
                listaCompras.setAdapter(null);
                Toast.makeText(contexto, "Lista vazia. Cadastre uma Compra!  ", Toast.LENGTH_LONG).show();//toast serve para  notificar. Sao as mensagens do android
            }
        }
        else
        {
            ArrayAdapter<Compras> adaptador = new ArrayAdapter<Compras>(contexto,
                    android.R.layout.simple_list_item_single_choice, produtosListados);// colocar a lista de clientes dentro do listView
            listaCompras.setAdapter(adaptador);
        }
        }

    }

