package com.example.cp

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

class TelaCadastrar : Fragment() {

    private var _binding:FragmentTelaCadastrarBinding?=null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnVoltarTelaCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_telaCadastrar_to_telaUsuarios)
        }
        binding.btnAdicionarTelaCadastrar.setOnClickListener {
            if(validarUsuario())
            {
                criarUsusario()
                findNavController().navigate(R.id.action_telaCadastrar_to_telaUsuarios)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTelaCadastrarBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    fun validarUsuario():Boolean
    {
        if(binding.inputEmailTelaCadastrar.text.isBlank())
        {
            Toast.makeText(this.context,"Campo de email deve ser preenchido",Toast.LENGTH_SHORT).show()
            return false
        }
        else if(!binding.inputEmailTelaCadastrar.text.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")))
        {
            Toast.makeText(this.context,"O email deve ser valido",Toast.LENGTH_SHORT).show()
            return false
        }
       // else if(checarExistenciaDeUsuario(binding.inputEmailTelaCadastrar.text.toString()))
        //{
          //  Toast.makeText(this.context, "Não podem haver emails duplicados", Toast.LENGTH_SHORT).show()
           // return false
       // }
        else if(binding.senhaTelaCadastro.text.isBlank())
        {
            Toast.makeText(this.context,"Campo de senha deve ser preenchido",Toast.LENGTH_SHORT).show()
            return false
        }
        else if(binding.senhaTelaCadastro.text.length<8 || binding.senhaTelaCadastro.text.length>12)
        {
            Toast.makeText(this.context,"Campo de senha deve conter entre 8 e 12 caracteres",Toast.LENGTH_SHORT).show()
            return false
        }
        else
        {
            Toast.makeText(this.context, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    fun criarUsusario()
    {
        Handler(Looper.getMainLooper()).post {
            val dao = Aplicacao.bancoDeDados.userDao()
            val email=binding.inputEmailTelaCadastrar.text.toString()
            val senha=binding.senhaTelaCadastro.text.toString()
            val habilitadoALogin=binding.checkBoxTelaCadastro.isChecked

            dao.inserirTodos(arrayListOf(Usuario(email,senha,habilitadoALogin)))
        }
    }

}