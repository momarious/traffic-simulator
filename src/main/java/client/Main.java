package src.main.java.client;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import src.main.java.simulator.Vehicle;

import javax.swing.JComboBox;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] DIRECTION = { "GAUCHE", "DROITE" };
	private JTextField textFieldMin;
	private JTextField textFieldMax;

	public Main() throws UnknownHostException, IOException {
		setLocation(0, -17);
		setSize(new Dimension(346, 329));
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Ajouter un v\u00E9hicule");
		lblNewLabel.setBounds(122, 28, 143, 24);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Direction");
		lblNewLabel_1.setBounds(32, 90, 60, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Dur\u00E9e munimum");
		lblNewLabel_1_1.setBounds(32, 125, 95, 24);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Dur\u00E9e maximum");
		lblNewLabel_1_1_1.setBounds(32, 160, 95, 24);
		getContentPane().add(lblNewLabel_1_1_1);

		textFieldMin = new JTextField();
		textFieldMin.setColumns(10);
		textFieldMin.setBounds(148, 127, 96, 20);
		getContentPane().add(textFieldMin);

		textFieldMax = new JTextField();
		textFieldMax.setColumns(10);
		textFieldMax.setBounds(148, 162, 96, 20);
		getContentPane().add(textFieldMax);

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(DIRECTION);
		JComboBox<String> comboBoxDirection = new JComboBox<String>(comboBoxModel);
		comboBoxDirection.setBounds(148, 91, 96, 22);
		getContentPane().add(comboBoxDirection);

		// Client
		@SuppressWarnings("resource")
		Socket serverSocket = new Socket("localhost", 7777);
		OutputStream outputStream = serverSocket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		JButton btnNewButtonSaveVehicle = new JButton("Ajouter le vehicule");
		btnNewButtonSaveVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String direction = comboBoxDirection.getSelectedItem().toString();
					int min = Integer.parseInt(textFieldMin.getText());
					int max = Integer.parseInt(textFieldMax.getText());

					if (textFieldMin.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Le minimum ne doit pas etre vide", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else if (textFieldMax.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Le maximum ne doit pas etre vide",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} else if (min > max) {
						JOptionPane.showMessageDialog(null, "Le minimum ne doit pas etre supperieur au maximum",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} else if (min < 0) {
						JOptionPane.showMessageDialog(null, "Le minimum ne doit pas etre negatif", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else if (max < 0) {
						JOptionPane.showMessageDialog(null, "Le maximum ne doit pas etre negatif", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Vehicle vehicle = new Vehicle(direction, min, max);
						objectOutputStream.writeObject(vehicle);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonSaveVehicle.setBounds(155, 199, 89, 23);
		getContentPane().add(btnNewButtonSaveVehicle);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Main frame = new Main();
		frame.setVisible(true);
	}
}
