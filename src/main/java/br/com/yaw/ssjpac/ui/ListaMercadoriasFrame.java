package br.com.yaw.ssjpac.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.stereotype.Component;

import br.com.yaw.ssjpac.model.Mercadoria;

/**
 * Tela principal da aplicação. Apresenta uma lista com as mercadorias cadastradas. 
 * 
 * <p>A partir dessa tela é possível criar/editar ou pesquisar mercadoria.</p>
 * 
 * <p>
 *  <code>ListaMercadoriasFrame</code> é mapeada como <code>@Component</code> do Spring. 
 *  Dessa forma uma instância de <code>ListaMercadoriasFrame</code> pode ser criada e gerenciada
 *  pelo Spring, favorecendo a Inversão de Controle <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @author YaW Tecnologia
 */
@SuppressWarnings("serial")
@Component
public class ListaMercadoriasFrame extends JFrame {
	
	private MercadoriaTable tabela;
	private JScrollPane scrollPane;
	private JButton bNewMercadoria;
	private JButton bFindMercadoria;
	private JButton bRefreshLista;
	private JMenuBar menubar;
	private MenuF1 menuAjuda;
	private JMenuItem menuSobre;
	
	public ListaMercadoriasFrame() {
		setTitle("Lista de Mercadoria");
		
		inicializaComponentes();
		adicionaComponentes();
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void inicializaComponentes() {
		tabela = new MercadoriaTable();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);

		bNewMercadoria = new JButton("Nova");
		bNewMercadoria.setActionCommand("novaMercadoriaAction");
		bNewMercadoria.setMnemonic(KeyEvent.VK_N);
		
		bFindMercadoria = new JButton("Buscar");
		bFindMercadoria.setActionCommand("buscarMercadoriasAction");
		bFindMercadoria.setMnemonic(KeyEvent.VK_B);
		
		bRefreshLista = new JButton("Atualizar");
		bRefreshLista.setActionCommand("atualizarMercadoriasAction");
		bRefreshLista.setMnemonic(KeyEvent.VK_A);
		
		menubar = new JMenuBar();
		menuAjuda = new MenuF1("Ajuda");
		menuAjuda.setMnemonic(KeyEvent.VK_J);
        menuSobre = new JMenuItem("Sobre    - F1");
        
        menuAjuda.add(menuSobre);
        menubar.add(menuAjuda);
        setJMenuBar(menubar);
	}
	
	private void adicionaComponentes(){
		add(scrollPane);
		JPanel panel = new JPanel();
		panel.add(bNewMercadoria);
		panel.add(bFindMercadoria);
		panel.add(bRefreshLista);
		add(panel, BorderLayout.SOUTH);
	}
	
	public JButton getNewButton() {
		return bNewMercadoria;
	}

	public JButton getRefreshButton() {
		return bRefreshLista;
	}
	
	public JButton getFindButton() {
		return bFindMercadoria;
	}
	
	public void refreshTable(List<Mercadoria> mercadorias) {
		tabela.reload(mercadorias);
	}
	
	public Mercadoria getSelectedMercadoria() {
		return tabela.getMercadoriaSelected();
	}
	
	public MercadoriaTable getTable() {
		return tabela;
	}
	
	public JMenuItem getMenuSobre() {
		return menuSobre;
	}
	
	public MenuF1 getMenuAjuda() {
		return menuAjuda;
	}

}
