



class MainWindow extends JFrame {

    private ArrayList<String> lista = new ArrayList<>();
    private DefaultListModel<String> model;
    private JList<String> ProductoJList = new JList<>();
    private ArrayList<String> cantidadProductos = new ArrayList<>();

    public MainWindow(String usuario) {
        setTitle("Gestor - Usuario: " + usuario);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultListModel<>();
        ProductoJList = new JList<>(model);

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelBotones.add(crearBoton("Agregar", e -> agregar()));
        panelBotones.add(crearBoton("Mostrar", e -> mostrar()));
        panelBotones.add(crearBoton("Eliminar", e -> eliminar()));
        panelBotones.add(crearBoton("Limpiar", e -> limpiar()));

        add(new JScrollPane(ProductoJList), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton crearBoton(String texto, ActionListener listener) {
        JButton btn = new JButton(texto);
        btn.addActionListener(listener);
        return btn;
    }
