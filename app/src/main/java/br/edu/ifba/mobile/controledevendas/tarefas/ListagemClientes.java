package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBD;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class ListagemClientes extends  AsyncTask<Void, Void, List<Cliente>> {

    private  Context contexto = null;
    private ListView listaClientes = null;

    public ListagemClientes(Context contexto, ListView listaClientes)
    {
        this.contexto = contexto;
        this.listaClientes = listaClientes;

    }

    @Override
    protected List<Cliente> doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano


        List<Cliente> clientes = FachadaBD.getInstancia().listarClientes();



        return clientes;
    }

    @Override
    protected void onPostExecute(List<Cliente> clientes)//depois da execucao// somente aqui que deve exibir a mensagem
    {
        if(clientes.isEmpty()){
            listaClientes.setAdapter(null);
            Toast.makeText(contexto,"Lista vazia. Cadastre um Cliente!  ", Toast.LENGTH_LONG).show();//toast serve para  notificar. Sao as mensagens do android
        }
        else
        {
            ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(contexto,
                    android.R.layout.simple_list_item_single_choice, clientes);// colocar a lista de clientes dentro do listView
            listaClientes.setAdapter(adaptador);
        }
        }

    }

