package de.vault.helpers;

import de.vault.mysql.MySql;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Autor: KeinJulian
 * Version: 1.0.0
 * Erstellt am: 10.10.2021
 */

public class HelperClass {

    public static String getMoney(String uuid) {
        ResultSet rs = MySql.getResult("SELECT * FROM Vault WHERE UUID='" + uuid + "'"); // Sende den MySql Befehl zum einholen von Informationen
        try {
            while(rs.next()) {
                return rs.getString("Money"); // Gebe das Ergebnis aus
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "-1"; // Falls kein ergebnis vorhanden, da kein Eintrag vorliegt, gebe "-1" aus
    }

    public static void setMoney(String uuid, Double amount, boolean exists) {
        if (exists) { // Falls der Boolean exists true ist
            MySql.update("UPDATE Vault SET Money='" + amount + "' WHERE UUID='" + uuid + "'"); // Update den Eintrag
        } else {                                                                                   // Falls exists false ist
            MySql.update("INSERT INTO Vault (UUID, Money) VALUES ('" + uuid + "','" + amount + "')"); // Erstelle einen neuen Eintrag
        }
    }

    public static void addMoney(OfflinePlayer t, Double amount) {
        Double currentAmount = Double.valueOf(HelperClass.getMoney(t.getUniqueId().toString())); // Erstelle einen Double mit dem Wert von "getMoney()"
        if (HelperClass.getMoney(t.getUniqueId().toString()).equalsIgnoreCase("-1")) { // Falls der Wert von "getMoney()" "-1" beträgt, existiert kein Eintrag, deshalb muss ein neuer erstellt werden
            HelperClass.setMoney(t.getUniqueId().toString(), currentAmount += amount, false); // Rufe die Methode "setMoney()" mit den neuen Werten auf. Der Boolean "false" steht dafür, dass noch kein Eintrag exestiert
        } else { // Falls ein Eintrag exestiert
            HelperClass.setMoney(t.getUniqueId().toString(), currentAmount += amount, true); // Rufe die Methode "setMoney()" mit den neuen Werten auf. Der Boolean "true" steht dafür, dass bereits ein Eintrag exestiert
        }
    }

    public static double shorten(double d)  { // Kürze den double "d" auf zwei Nachkomma-Stellen
        double scale = Math.pow(10, 2);
        double doubleM1 = Math.round(d*scale)/scale;
        return (doubleM1);
    }

    public static String getFormatted(double d) { // Formatiere einen double so, dass dieser alle drei Zeichen ein Kommata enthält
        return NumberFormat.getNumberInstance(Locale.US).format(d);
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
