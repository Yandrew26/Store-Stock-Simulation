package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.EmptyStringException;
import exception.NegativeNumberException;
import model.Event;
import model.EventLog;
import model.Product;

public class StoreGUI implements ActionListener {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private StoreApp sa;
    private JFrame frame;
    private JPanel homePanel;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel viewPanel;
    private JPanel stockPanel;
    private JPanel productPanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton stockButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton[] productButtons;
    private JButton backButton1;
    private JButton backButton2;
    private JButton backButton3;
    private JButton backButton4;
    private JButton backButton5;
    private JButton confirmButton;
    private JButton removeProductButton;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productQuantityField;
    private JTextField removeProductField;
    private JLabel messageLabel;
    private Container container;
    private CardLayout cardLayout;
    private ImageIcon imageIcon;

    /**
     * Constructor sets up all components in the app.
     */
    public StoreGUI() {
        initializeStore();
        intializeButtons();
        intializeTextFields();

        homePanel();
        addPanel();
        createLayout();

        buttonListener();

        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);

        confirmButtonListener();
        removeProductButtonListener();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString());
                }
                System.exit(0);
            }
        });
    }

    /*
     * EFFECTS: keeps track of the action when a specific button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        addButtonListener(e);
        removeButtonListener(e);
        viewButtonListener(e);
        stockButtonListener(e);
        if (e.getSource() == saveButton) {
            sa.saveWorkRoom();
        }
        if (e.getSource() == loadButton) {
            sa.loadWorkRoom();
        }
        if (e.getSource() == backButton1) {
            cardLayout.show(container, "home");
        }
        if (e.getSource() == backButton2) {
            cardLayout.show(container, "home");
        }
        if (e.getSource() == backButton3) {
            cardLayout.show(container, "home");
        }
        if (e.getSource() == backButton4) {
            cardLayout.show(container, "home");
        }
        productsButtonListener(e);
    }

    /*
     * EFFECTS: when remove product button is clicked, the selected product is
     * removed from the store
     */
    private void removeProductButtonListener() {
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(removeProductField.getText());
                sa.doRemoveProduct(num);
                cardLayout.show(container, "home");
            }
        });
    }

    /*
     * EFFECTS: when confirm button is clicked and a new product is added to the
     * store
     */
    private void confirmButtonListener() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = productNameField.getText();
                    String price = productPriceField.getText();
                    String quantity = productQuantityField.getText();
                    sa.doAddProduct(name, price, quantity);
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Product added");
                    productNameField.setText("");
                    productPriceField.setText("");
                    productQuantityField.setText("");
                } catch (NumberFormatException | NegativeNumberException ex) {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid price or quantity inputs");
                } catch (EmptyStringException ex) {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid name input");
                }
            }
        });
    }

    /*
     * EFFECTS: setup all actionListener for all buttons
     */
    private void buttonListener() {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        viewButton.addActionListener(this);
        stockButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        backButton1.addActionListener(this);
        backButton2.addActionListener(this);
        backButton3.addActionListener(this);
        backButton4.addActionListener(this);
        backButton5.addActionListener(this);
    }

    /*
     * EFFECTS: creates and setup cardlayout
     */
    private void createLayout() {
        container.setLayout(cardLayout);
        container.setSize(WIDTH, HEIGHT);
        container.add("home", homePanel);
        container.add("add", addPanel);
        container.add("remove", removePanel);
        container.add("view", viewPanel);
        container.add("stock", stockPanel);
        container.add("product", productPanel);
        cardLayout.show(container, "home");
    }

    /*
     * EFFECTS: sets up add products panel
     */
    private void addPanel() {
        messageLabel = new JLabel();
        messageLabel.setFont(new Font(null, Font.BOLD, 20));
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.add(new JLabel("Enter product name:"));
        addPanel.add(productNameField);
        addPanel.add(new JLabel("Enter product price:"));
        addPanel.add(productPriceField);
        addPanel.add(new JLabel("Enter product quantity:"));
        addPanel.add(productQuantityField);
        addPanel.add(confirmButton);
        addPanel.add(backButton1);
        addPanel.add(messageLabel);
    }

    /*
     * EFFECTS: set up home panel
     */
    private void homePanel() {
        try {
            imageIcon = new ImageIcon(getClass().getResource("shelf.png"));
            Image newimg = imageIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
        } catch (Exception e) {
            System.out.println("Image not found!");
        }
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.add(new JLabel(imageIcon));
        homePanel.add(addButton);
        homePanel.add(removeButton);
        homePanel.add(viewButton);
        homePanel.add(stockButton);
        homePanel.add(saveButton);
        homePanel.add(loadButton);
    }

    /*
     * EFFECTS: intialize all text fields
     */
    private void intializeTextFields() {
        productNameField = new JTextField();
        productPriceField = new JTextField();
        productQuantityField = new JTextField();
        removeProductField = new JTextField();
        productNameField.setMaximumSize(new Dimension(500, 25));
        productPriceField.setMaximumSize(new Dimension(500, 25));
        productQuantityField.setMaximumSize(new Dimension(500, 25));
        removeProductField.setMaximumSize(new Dimension(100, 25));
    }

    /*
     * EFFECTS: Intialize all buttons
     */
    private void intializeButtons() {
        addButton = new JButton("Add Products");
        removeButton = new JButton("Remove Products");
        viewButton = new JButton("View Products");
        stockButton = new JButton("Check Stock");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        backButton1 = new JButton("Back");
        backButton2 = new JButton("Back");
        backButton3 = new JButton("Back");
        backButton4 = new JButton("Back");
        backButton5 = new JButton("Back");
        confirmButton = new JButton("Confirm");
        removeProductButton = new JButton("Remove");
    }

    /*
     * EFFECTS: initializes StoreApp, frame, and panels
     */
    private void initializeStore() {
        try {
            sa = new StoreApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        frame = new JFrame("StoreApp");
        homePanel = new JPanel();
        addPanel = new JPanel();
        removePanel = new JPanel();
        viewPanel = new JPanel();
        stockPanel = new JPanel();
        productPanel = new JPanel();
        container = new Container();
        cardLayout = new CardLayout();
    }

    /*
     * EFFECTS: when add button is clicked, the application switches to the addPanel
     */
    private void addButtonListener(ActionEvent e) {
        if (e.getSource() == addButton) {
            cardLayout.show(container, "add");
            messageLabel.setText("");
        }
    }

    /*
     * EFFECTS: when remove button is clicked, the application switches to the
     * removePanel and prints out every product with corresponding product numbers.
     * Also a textfield, two buttons are added.
     */
    private void removeButtonListener(ActionEvent e) {
        if (e.getSource() == removeButton) {
            cardLayout.show(container, "remove");
            removeProductField.setText("");
            removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.Y_AXIS));
            removePanel.removeAll();
            for (int i = 0; i < sa.getAllProducts().size(); i++) {
                Product p = sa.getAllProducts().get(i);
                removePanel.add(
                        new JLabel(i + " -> " + p.getName() + " $" + p.getPrice() + " quantity: " + p.getQuantity()));
            }
            removePanel.add(new JLabel("Select product number:"));
            removePanel.add(removeProductField);
            removePanel.add(removeProductButton);
            removePanel.add(backButton2);
            removeProductButton.addActionListener(this);
        }
    }

    /*
     * EFFECTS: when view button or back button from the product panel is clicked, 
     */
    private void viewButtonListener(ActionEvent e) {
        if (e.getSource() == viewButton || e.getSource() == backButton5) {
            cardLayout.show(container, "view");
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.removeAll();
            productButtons = new JButton[sa.getAllProducts().size()];
            for (int i = 0; i < sa.getAllProducts().size(); i++) {
                String name = sa.getAllProducts().get(i).getName();
                productButtons[i] = new JButton(name);
                viewPanel.add(productButtons[i]);
                productButtons[i].addActionListener(this);
            }
            viewPanel.add(backButton3);
        }
    }

    private void stockButtonListener(ActionEvent e) {
        if (e.getSource() == stockButton) {
            cardLayout.show(container, "stock");
            stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
            stockPanel.removeAll();
            stockPanel.add(new JLabel("In stock products:"));
            for (int i = 0; i < sa.getProductsInStock().size(); i++) {
                Product p = sa.getAllProducts().get(i);
                stockPanel.add(
                        new JLabel(i + " -> " + p.getName() + " $" + p.getPrice() + " quantity: " + p.getQuantity()));
            }
            stockPanel.add(new JLabel("------------------------------------------------------"));
            stockPanel.add(new JLabel("Out of stock products:"));
            for (int i = 0; i < sa.getProductsOutOfStock().size(); i++) {
                Product p = sa.getAllProducts().get(i);
                stockPanel.add(
                        new JLabel(i + " -> " + p.getName() + " $" + p.getPrice() + " quantity: " + p.getQuantity()));
            }
            stockPanel.add(backButton4);
        }
    }

    private void productsButtonListener(ActionEvent e) {
        try {
            JButton src = (JButton) e.getSource();
            for (int i = 0; i < sa.getAllProducts().size(); i++) {
                String name = sa.getAllProducts().get(i).getName();
                Double price = sa.getAllProducts().get(i).getPrice();
                int quantity = sa.getAllProducts().get(i).getQuantity();
                if (src == productButtons[i]) {
                    productPanel.removeAll();
                    cardLayout.show(container, "product");
                    productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                    productPanel.add(new JLabel("Name: " + name));
                    productPanel.add(new JLabel("Price: $" + price));
                    productPanel.add(new JLabel("Quantity: " + quantity));
                    productPanel.add(backButton5);
                }
            }
        } catch (Exception ex) {
            System.out.print("");
        }
    }
}
