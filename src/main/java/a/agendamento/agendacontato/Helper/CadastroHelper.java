package a.agendamento.agendacontato.Helper;

import android.widget.EditText;

import a.agendamento.agendacontato.Activity.Cadastro_Contato;
import a.agendamento.agendacontato.R;
import a.agendamento.agendacontato.modelo.Contato;

public class CadastroHelper {
    private Contato contato;
    private EditText nome;
    private EditText telefone;
    private EditText endereco;

    public CadastroHelper(Cadastro_Contato activity) {
        this.contato = new Contato();
        this.nome = (EditText) activity.findViewById(R.id.cadastro_nome);
        this.telefone = (EditText) activity.findViewById(R.id.cadastro_telefone);
        this.endereco = (EditText) activity.findViewById(R.id.cadastro_endereco);
    }

    public Contato pegaContatoDoCadastro(){
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setEndereco(endereco.getText().toString());

        return contato;
    }

    public boolean temNome(){return !nome.getText().toString().isEmpty();}

    public void mostraErro(){nome.setError("Campo nome não pode ser vazio");}
}
