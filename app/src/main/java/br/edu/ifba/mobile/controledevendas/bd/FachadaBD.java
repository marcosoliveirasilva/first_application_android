package br.edu.ifba.mobile.controledevendas.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FachadaBD extends SQLiteOpenHelper {
	private static FachadaBD instancia = null;

	public static FachadaBD criarInstancia(Context context)
	{
		if(instancia == null)
		{
			instancia = new FachadaBD (context);
		}
		return instancia;
	}

	public static FachadaBD getInstancia()
	{
		return instancia;
	}

	private static String NOME_BANCO = "ControleNotas";
	private static int VERSAO_BANCO = 1;

	public FachadaBD(Context context) {
		super(context,NOME_BANCO, null, VERSAO_BANCO);

	}

	private static String COMANDO_CRIACAO_TABELA_CLIENTES =
			"CREATE TABLE CLIENTES(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "NOME TEXT, EMAIL TEXT, TELEFONE TEXT)";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COMANDO_CRIACAO_TABELA_CLIENTES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
		// TODO Auto-generated method stub
	}

	// metodos de criacao de um CRUD utilizando o SQLite
	
	public long inserir(Cliente cliente) {

		SQLiteDatabase db = this.getWritableDatabase(); //this chama a prorpia classe
		ContentValues valores= new ContentValues(); // objeto que vai pegar as notas das clientes
		valores.put("NOME", cliente.getNome());
		valores.put("EMAIL", cliente.getEmail());// colocando as dicicplinas em valores
		valores.put("TELEFONE", cliente.getTelefone());

		long codigo = db.insert ("CLIENTES",null,valores); //inserindo as clientes no banco de dadaos/ codigo vai receber um id
		return codigo;
	}

	public long atualizar(Cliente cliente) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores= new ContentValues(); // objeto que vai pegar as notas das clientes
		valores.put("NOME", cliente.getNome());
		valores.put("EMAIL", cliente.getEmail());// colocando as dicicplinas em valores
		valores.put("TELEFONE", cliente.getTelefone());
		long codigo = db.update("CLIENTES", valores,"CODIGO ="+ cliente.getCodigo(), null); //atualizando a tabela onde o codigo é esse que estamos passando/ update também retorna m sodigo
		return codigo;

	}



	public int remover(Cliente cliente) {
		SQLiteDatabase db= this.getWritableDatabase();

		return db.delete("CLIENTES", "CODIGO="+cliente.getCodigo(),null);//deletando as diciplinas onde o codigo é ge.codigo
	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();// criando lista de clientes

		SQLiteDatabase db = this.getReadableDatabase();

		String selecao = " SELECT CODIGO, NOME, EMAIL, TELEFONE FROM CLIENTES";
		Cursor cursor = db.rawQuery(selecao, null); //executando esse comando selecao que mandamos / query retorna um recurso que é um cursor que  é um apontador para cada linha de registro.nesse caso é as proprias linhas da tabela
		if(cursor!=null)
		{
			boolean temProximo = cursor.moveToFirst();// indo para a primeira linha do registro da tabela

			while (temProximo)
			{
				Cliente cliente = new Cliente();
				cliente.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO" )));// setando os campos na tabela cliente
				cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
				cliente.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
				cliente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));

				clientes.add(cliente);
				temProximo = cursor.moveToNext();// percorrendo toda a linha
			}
		}
		return  clientes;

	}

}