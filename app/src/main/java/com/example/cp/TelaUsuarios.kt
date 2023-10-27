package com.example.cp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.cp.databinding.FragmentTelaCadastrarBinding
import com.example.cp.databinding.FragmentTelaUsuariosBinding

class TelaUsuarios : Fragment() {

    private var _binding: FragmentTelaUsuariosBinding?=null
    private val binding get() =_binding!!
    private  var listaUsuarios:List<Usuario>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdicionarTelaUsuarios.setOnClickListener {
            findNavController().navigate(R.id.action_telaUsuarios_to_telaCadastrar)
        }
    }

    override fun onResume() {
        super.onResume()
        carregarUsuarios()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTelaUsuariosBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun carregarUsuarios(){

        Handler(Looper.getMainLooper()).post {
            val dao = Aplicacao.bancoDeDados.userDao()
            listaUsuarios = dao.recuperarTodos()
            apresentarDados()
            inicialiarBotoesUsuarios()
        }
    }
    fun inicialiarBotoesUsuarios()
    {
        if(listaUsuarios!!.size>0)
        {
            binding.btnUsuario1.setOnClickListener {
                Aplicacao.ponteiroDeEmail=listaUsuarios!!.get(0).email.toString()
                findNavController().navigate(R.id.action_telaUsuarios_to_telaEditar)
            }
        }
        else
        {
            binding.btnUsuario1.setOnClickListener{}
        }
        if(listaUsuarios!!.size>1)
        {
            binding.btnUsuario2.setOnClickListener {
                Aplicacao.ponteiroDeEmail=listaUsuarios!!.get(1).email.toString()
                findNavController().navigate(R.id.action_telaUsuarios_to_telaEditar)
            }
        }
        else
        {
            binding.btnUsuario2.setOnClickListener{}
        }
        if(listaUsuarios!!.size>2)
        {
            binding.btnUsuario3.setOnClickListener {
                Aplicacao.ponteiroDeEmail=listaUsuarios!!.get(2).email.toString()
                findNavController().navigate(R.id.action_telaUsuarios_to_telaEditar)
            }
        }
        else
        {
            binding.btnUsuario3.setOnClickListener{}
        }
    }
    fun apresentarDados()
    {
        if(listaUsuarios!!.size>0)
        {
            binding.usuario1Label.text="Email: "+listaUsuarios!!.get(0).email.toString()
            if(listaUsuarios!!.get(0).HabilitadoParaFazerLogin)
            {
                binding.usuario1Label2.text="Acesso: ativo"
            }
            else
            {
                binding.usuario1Label2.text="Acesso: desativado"
            }
        }
        else
        {
            binding.usuario1Label.text=""
            binding.usuario1Label2.text=""
        }
        if(listaUsuarios!!.size>1)
        {
            binding.Usuario2Label.text="Email: "+listaUsuarios!!.get(1).email.toString()
            if(listaUsuarios!!.get(1).HabilitadoParaFazerLogin)
            {
                binding.usuario2Label2.text="Acesso: ativo"
            }
            else
            {
                binding.usuario2Label2.text="Acesso: desativado"
            }
        }
        else
        {
            binding.Usuario2Label.text=""
            binding.usuario2Label2.text=""
        }
        if(listaUsuarios!!.size>2)
        {
            binding.usuario3Label.text="Email: "+listaUsuarios!!.get(2).email.toString()
            if(listaUsuarios!!.get(2).HabilitadoParaFazerLogin)
            {
                binding.usuario3Label2.text="Acesso: ativo"
            }
            else
            {
                binding.usuario3Label2.text="Acesso: desativado"
            }
        }
        else
        {
            binding.usuario3Label.text=""
            binding.usuario3Label2.text=""
        }
    }
}

