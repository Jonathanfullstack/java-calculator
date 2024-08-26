import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoList extends JFrame implements ActionListener {

    // Componentes da interface
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton, completeButton;

    public ToDoList() {
        // Configuração da janela principal
        setTitle("Lista de Tarefas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo de lista para armazenar tarefas
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Campo de texto para adicionar novas tarefas
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));

        // Botões de ação
        addButton = new JButton("Adicionar Tarefa");
        deleteButton = new JButton("Excluir Tarefa");
        completeButton = new JButton("Completar Tarefa");

        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        completeButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Adiciona os botões e o campo de texto ao painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 4, 10, 10));
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(completeButton);

        // Adiciona componentes à janela principal
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Adiciona ActionListeners aos botões
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        completeButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            }
        } else if (e.getSource() == deleteButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        } else if (e.getSource() == completeButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String completedTask = listModel.getElementAt(selectedIndex) + " (Concluída)";
                listModel.set(selectedIndex, completedTask);
                taskList.clearSelection();
            }
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
