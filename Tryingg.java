package opson;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

public class Tryingg extends JFrame {
	JMenuBar menuBar;
	JPanel dataPanel;
	JPanel tablePanel;
	DefaultTableModel model;
	JTable table;
	JPanel lowerPanel;
	JTextField txtName, txtid, txtcourse;
	// JRadioButton rdMale, rdFemale;
	// ButtonGroup bgGroup;
	JCheckBox covidStatus;
	JScrollPane scrollPane;
	ButtonGroup bg;
	public String name, gender, covid, address, course;

	JButton btnSave, btnUpdate, btnDelete, btnClear;
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid", "root", "root@123");
	private JFrame frame;

	private JTextField nameText;
	private JTextField addressText;
	JRadioButton maleRadio, femaleRadio;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) throws SQLException {
		Tryingg window = new Tryingg();
		window.frame.setVisible(true);
	}

	Tryingg() throws SQLException {
		initialize();
		getMenu();
		tableUI();
		dataUI();

		setTitle("Sample App");
		setVisible(true);
		// setMinimumSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenu());

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1438, 838);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel maleDataentry = new JPanel();
		maleDataentry
				.setBorder(new TitledBorder(null, "Data Entry", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		maleDataentry.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Entry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		maleDataentry.setBounds(10, 10, 778, 398);
		frame.getContentPane().add(maleDataentry);
		maleDataentry.setLayout(null);

		JLabel nameLabel = new JLabel("Name:");
//		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(163, 98, 56, 13);
		maleDataentry.add(nameLabel);

		nameText = new JTextField();
		nameText.setBounds(237, 97, 267, 19);
		maleDataentry.add(nameText);
		nameText.setColumns(10);

		JLabel addressLabel = new JLabel("Address:");
//		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addressLabel.setBounds(157, 137, 62, 13);
		maleDataentry.add(addressLabel);

		JLabel genderJradioLabel = new JLabel("Gender");
//		genderJradioLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		genderJradioLabel.setBounds(163, 184, 56, 13);
		maleDataentry.add(genderJradioLabel);

		addressText = new JTextField();
		addressText.setColumns(10);
		addressText.setBounds(237, 136, 267, 19);
		maleDataentry.add(addressText);

		maleRadio = new JRadioButton("Male");
//		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		maleRadio.setBounds(259, 181, 103, 21);
		maleDataentry.add(maleRadio);

		femaleRadio = new JRadioButton("Female");
//		femailDataentry.setFont(new Font("Tahoma", Font.BOLD, 12));
		femaleRadio.setBounds(370, 181, 103, 21);
		maleDataentry.add(femaleRadio);
		bg = new ButtonGroup();
		bg.add(maleRadio);
		bg.add(femaleRadio);

		JLabel lblNewLabel_1_1_1 = new JLabel("Covid Status");
//		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(125, 227, 94, 13);
		maleDataentry.add(lblNewLabel_1_1_1);

		covidStatus = new JCheckBox("Positive");
//		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		covidStatus.setBounds(259, 225, 93, 21);
		maleDataentry.add(covidStatus);

		JPanel buttonPannel = new JPanel();
		buttonPannel.setBorder(
				new TitledBorder(null, "Button Functionality", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		buttonPannel.setBorder(new TitledBorder(null, "Button Functionality", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPannel.setBounds(798, 10, 616, 398);
		frame.getContentPane().add(buttonPannel);
		buttonPannel.setLayout(null);

		btnSave = new JButton("Save");
		btnSave.setBounds(22, 25, 270, 160);
		buttonPannel.add(btnSave);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(22, 214, 270, 160);
		buttonPannel.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(319, 25, 270, 160);
		buttonPannel.add(btnUpdate);

		btnClear = new JButton("Clear");
		btnClear.setBounds(319, 214, 270, 160);
		buttonPannel.add(btnClear);

		lowerPanel = new JPanel();
		lowerPanel
				.setBorder(new TitledBorder(null, "List Of Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		listofdataPannel.setBorder(new TitledBorder(null, "List Of Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lowerPanel.setBounds(10, 435, 1404, 356);
		frame.getContentPane().add(lowerPanel);
		lowerPanel.setLayout(null);

	}

	private JMenuBar getMenu() {
		menuBar = new JMenuBar();

		// define top level menu
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");

		// creating sub menu
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem exitItem = new JMenuItem("Exit");

		// sub menu added inside file menu
		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		// top level menu added inside menu bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);

		return menuBar;
	}

	private JPanel dataUI() {

//	        

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String address = addressText.getText();

				String gender = "";
				String covid = "";
				if (maleRadio.isSelected()) {
					gender = "Male";
				} else {
					gender = "Female";
				}

				if (covidStatus.isSelected()) {
					covid = "Positive";
				} else {
					covid = "Negative";
				}

				try {
					PreparedStatement p;

					p = con.prepareStatement("insert into covid(Name,Address,Gender,Covid)values(?,?,?,?)");
					p.setString(1, name);
					p.setString(2, address);
					p.setString(3, gender);
					p.setString(4, covid);

					p.executeUpdate();
					refreshTable();
					btnClear.doClick();

					JOptionPane.showMessageDialog(btnSave, "Your data is sucessfully added");

				} catch (Exception exception) {
					exception.printStackTrace();
				}

				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						int selectedRowIndex = table.getSelectedRow();

						nameText.setText(model.getValueAt(selectedRowIndex, 1).toString());
						addressText.setText(model.getValueAt(selectedRowIndex, 2).toString());

						String gender = model.getValueAt(selectedRowIndex, 3).toString();
						if (gender.equals("Male")) {
							maleRadio.setSelected(true);
						} else {
							femaleRadio.setSelected(true);
						}
						String covid = model.getValueAt(selectedRowIndex, 4).toString();
						if (covid.equals("Positive")) {
							covidStatus.setSelected(true);
						} else {
							covidStatus.setSelected(false);
						}

					}
				});
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String row = JOptionPane.showInputDialog(dataPanel, "Please enter ID number to delete?", "Queries",
						JOptionPane.QUESTION_MESSAGE);
				int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you sure want to delete row?", "Warning",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						int rowDelete = Integer.parseInt(row);
						String query = "DELETE FROM covid WHERE id = ?";

						try {
							PreparedStatement statement = con.prepareStatement(query);
							statement.setInt(1, rowDelete);
							statement.execute();
							refreshTable();
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

//	                        model.removeRow(rowDelete - 1);
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(dataPanel, "You must enter valid number.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ArrayIndexOutOfBoundsException exception) {
						JOptionPane.showMessageDialog(dataPanel,
								"Provided row doesn't exist. Please enter valid row number.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				addressText.setText("");
				bg.clearSelection();
				covidStatus.setSelected(false);
				JOptionPane.showMessageDialog(btnClear, "The data has been cleared");

			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				String Name = nameText.getText().trim();
				String Address = addressText.getText().trim();
				String Gender = maleRadio.isSelected() ? "Male" : "Female";
				String COVID_19 = covidStatus.isSelected() ? "Positive" : "Negative";

				int ID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
				String query = "UPDATE covid SET Name = ?, Address = ? , Gender = ? , COVID =? WHERE ID = ?";
				try {
					PreparedStatement statement = con.prepareStatement(query);
					statement.setString(1, Name);
					statement.setString(2, Address);
					statement.setString(3, Gender);
					statement.setString(4, COVID_19);
					statement.setInt(5, ID);

					statement.executeUpdate();
					refreshTable();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

				btnClear.doClick();
				JOptionPane.showMessageDialog(dataPanel, "Record at row " + selectedRow + " is updated successfully",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}

		});

		return dataPanel;
	}

	private JPanel tableUI() {

		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID", "Name", "Address", "Gender", "Covid Status" });
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 32, 1384, 314);
		lowerPanel.add(scrollPane);

		// added event listener for row selection inside JTable
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// returns row number of selected row in JTable
				int selectedRow = table.getSelectedRow();
				// model.getValueAt(selectedRow, 0) returns a value for the cell at the given
				// row and column
				// .toString() returns string representation of the object
//	                txtid.setText(model.getValueAt(selectedRow, 0).toString());
				nameText.setText(model.getValueAt(selectedRow, 1).toString());
				addressText.setText(model.getValueAt(selectedRow, 2).toString());
				String gender = model.getValueAt(selectedRow, 3).toString();
				if (gender.equals("Male")) {
					maleRadio.setSelected(true);
				} else {
					femaleRadio.setSelected(true);
				}
				String covid = model.getValueAt(selectedRow, 4).toString();
				if (covid.equals("Positive")) {
					covidStatus.setSelected(true);
				} else {
					covidStatus.setSelected(false);
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		refreshTable();
		return lowerPanel;
	}

	private void refreshTable() {
		model.setRowCount(0);
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM covid");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				model.addRow(new Object[] { resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("address"), resultSet.getString("gender"), resultSet.getString("covid") });
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}