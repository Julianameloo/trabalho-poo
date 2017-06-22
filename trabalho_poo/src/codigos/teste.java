/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import java.util.Iterator;
import java.util.LinkedList;
import model.dao.Mot_passDAO;
import model.dao.MotoristaDAO;
import model.dao.PassageiroDAO;
import model.dao.UsuarioDAO;
import model.dao.VeiculoDAO;

public class teste {

    public static void main(String[] args) {
        VeiculoDAO vdao = new VeiculoDAO();
        Motorista m = new Motorista();
        Passageiro p = new Passageiro();
        UsuarioDAO udao = new UsuarioDAO();
        LinkedList l = new LinkedList();
        p.setCep("1");
        p.setCpf("0");
        p.setEnderecoResidencia("ascca ");
        p.setLogin("fa");
        p.setNome("u");
        p.setSenha("12e1da");
        int i = udao.criar(p);
        p.setId(i);
        PassageiroDAO pdao = new PassageiroDAO();
        pdao.criar(p);
        p.setId(4);
        
        //udao.excluir(m);
        int a = udao.verificarSenha("f", "12e1da");
        MotoristaDAO mdao = new MotoristaDAO();
        int b = pdao.verificaPassageiro(a);
        p = pdao.buscar(b);
        a = udao.verificarSenha("fs", "12e1da");
        int c = mdao.verificaMotorista(a);
        m = mdao.buscar(c);
        Mot_passDAO mpdao = new Mot_passDAO();
        mpdao.criar(m, p, false);
        l = mpdao.solicitacoes(m.getId_motorista());
        Iterator it = l.iterator();
        while(it.hasNext()){
            
        Usuario u = (Usuario) it.next();
            //System.out.println(u.getLogin());
        }
        mpdao.confirma(m.getId_motorista(), p.getId_passageiro());
        l = mpdao.motoristas(3);
        
        it = l.iterator();
        while(it.hasNext()){
        Usuario u = (Usuario) it.next();
            System.out.println(u.getLogin());
        }
        //m = mdao.buscar(b);
        //System.out.println(m.getLogin());
        //mdao.criar(m);
        //Usuario u = udao.buscar(1);
        //System.out.println(u.getLogin());
        /*l = udao.buscar("eai ");
        p.setNome("eai ");
        p.setId(47);
        udao.atualizar(p);
        
        Iterator it = l.iterator();
        while(it.hasNext()){
            
        Usuario u = (Usuario) it.next();
        if(pdao.verificaPassageiro(u.getId()) != -1)
            System.out.println(u.getLogin());
        }*/
    }
    
}
