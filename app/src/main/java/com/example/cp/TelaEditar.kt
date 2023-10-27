package com.example.cp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cp.databinding.FragmentTelaCadastrarBinding
import com.example.cp.databinding.FragmentTelaEditarBinding

class TelaEditar : Fragment() {

    private var _binding: FragmentTelaEditarBinding?=null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializarInputs()
        binding.btnExcluirTelaEditar.setOnClickListener {
            val builder =AlertDialog.Builder(this.context)
            builder.setMessage("Tem certeza que deseja Excluir o usuario?")
            builder.setPositiveButton("Excluir", DialogInterface.OnClickListener { dialog, id -> exlcluirUsuario()
                Toast.makeText(this.context,"Usuario deletado", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_telaEditar_to_telaUsuarios)
            })
            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id
                -> })
                val dialog = builder.create()
                dialog.show()
        }
        binding.btnVoltarTelaEditar.setOnClickListener {
            findNavController().navigate(R.id.action_telaEditar_to_telaUsuarios)
        }
        binding.btnAlterarTelaEditar.setOnClickListener {
            if(validarUsuario())
            {
                alterarUsuario()
                Toast.makeText(this.context,"Usuario alterado", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_telaEditar_to_telaUsuarios)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTelaEditarBinding.inflate(inflater,container,false)
        return binding.root
    }

    fun exlcluirUsuario()
    {
        Handler(Looper.getMainLooper()).post {
            val dao = Aplicacao.bancoDeDados.userDao()
            dao.deletar(dao.encontrarEmail(Aplicacao.ponteiroDeEmail))
        }
    }

    fun alterarUsuario()
    {
        Handler(Looper.getMainLooper()).post {
            val dao = Aplicacao.bancoDeDados.userDao()
            val email=binding.inputEmailTelaEditar.text.toString()
            val senha=binding.senhaTelaEditar.text.toString()
            val habilitadoALogin=binding.checkBoxTelaEditar.isChecked

            dao.alterarUsuario(Aplicacao.ponteiroDeEmail,email,senha,habilitadoALogin)
        }
    }

    fun inicializarInputs()
    {
        Handler(Looper.getMainLooper()).post {
            val dao = Aplicacao.bancoDeDados.userDao()

            val emailASerEditado= dao.encontrarEmail(Aplicacao.ponteiroDeEmail)
            binding.inputEmailTelaEditar.setText(emailASerEditado.email)
            binding.senhaTelaEditar.setText(emailASerEditado.senha)
        }
    }

    fun validarUsuario():Boolean
    {
        if(binding.inputEmailTelaEditar.text.isBlank())
        {
            Toast.makeText(this.context,"Campo de email deve ser preenchido", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(!binding.inputEmailTelaEditar.text.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")))
        {
            Toast.makeText(this.context,"O email deve ser valido", Toast.LENGTH_SHORT).show()
            return false
        }
        // else if(checarExistenciaDeUsuario(binding.inputEmailTelaCadastrar.text.toString()))
        //{
        //  Toast.makeText(this.context, "Não podem haver emails duplicados", Toast.LENGTH_SHORT).show()
        // return false
        // }
        else if(binding.senhaTelaEditar.text.isBlank())
        {
            Toast.makeText(this.context,"Campo de senha deve ser preenchido", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(binding.senhaTelaEditar.text.length<8 || binding.senhaTelaEditar.text.length>12)
        {
            Toast.makeText(this.context,"Campo de senha deve conter entre 8 e 12 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
        else
        {
            Toast.makeText(this.context, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}