import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

private void agregar() {
        Scanner texto = new Scanner(System.in);
        System.out.println("Agregar item: ");
        String item = texto.nextLine();

        if (item != null && !item.trim().isEmpty()) {
            lista.add(item);
            model.addElement(item);
            Scanner cantidad = new Scanner(System.in);
            while (true) {
                System.out.println("Agregar cantidad: ");
                String stock = cantidad.nextLine();
                try {
                    int valor = Integer.parseInt(stock);

                    if (stock != null && !stock.trim().isEmpty()) {
                        System.out.println("Cantidad agregado: " + stock);
                        cantidadProductos.add(stock);
                        break;

                    } else {
                        System.err.println("Cantidad no válida");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Cantidad no válida");
                }
            }

        } else {
            System.err.println("Item vacío");

        }

    }

    private void mostrar() {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La lista está vacía");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("Item: " + lista.get(i) + " - Cantidad: " + cantidadProductos.get(i));
            }

        }
    }

    private void eliminar() {
        Scanner texto = new Scanner(System.in);
        Scanner cantidad = new Scanner(System.in);
        System.out.println("Item a eliminar: ");
        String item = texto.nextLine();
        if (item != null && !item.trim().isEmpty()) {
            while (true) {

                System.out.println("Cantidad a eliminar: ");
                String stock = cantidad.nextLine();

                try {
                    int numero = Integer.parseInt(stock);

                    if (stock != null && !stock.trim().isEmpty() && numero >= 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            if (lista.get(i).equals(item)) {
                                if (Integer.parseInt(cantidadProductos.get(i)) >= numero) {
                                    int nuevaCantidad = Integer.parseInt(cantidadProductos.get(i)) - numero;
                                    if (nuevaCantidad == 0) {
                                        lista.remove(i);
                                        cantidadProductos.remove(i);
                                        model.remove(i);
                                        System.out.println("Item eliminado completamente: " + item);
                                    } else {
                                        cantidadProductos.set(i, String.valueOf(nuevaCantidad));
                                        System.out.println("Cantidad actualizada para " + item + ": " + nuevaCantidad);
                                    }
                                } else {
                                    System.err.println("No hay suficiente cantidad para eliminar");

                                }
                                return;
                            }
                                
                                
                            

                        }
                    } else {
                        System.out.println("Item no encontrado: " + item);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Cantidad no válida");
                }
            }
        } else {
            System.err.println("Item vacío");
            return;
        }

    }

    private void limpiar() {
        lista.clear();
        model.clear();
    }
