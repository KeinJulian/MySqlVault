# MySqlVault for Spigot 1.8.8
VaultPlugin working with MySql databases to sync data between servers (GrieferGames Bewerbung)

--------------------------------------------------------------------------------------------------------------

Vorwort:
Guten Tag liebes GrieferGames-Teammitglied! Dies ist das Plugin, welches ich ausgesucht habe, als Bewerbung einzureichen. 
Ich habe dieses Plugin gewählt, da ich finde, dass es meine Fähigkeiten, wie z.B. MySql Programmierung, das Arbeiten mit Config-Dateien, meine
Sorgfalt bei Features und Fehlerbehebung, sowie das allgemeine Programmieren von Bukkit Plugins, ziemlich gut wiederspiegelt.

Falls Fragen oder Probleme aufkommen, würde ich mich darüber freuen, wenn Sie mich anschreiben und Fragen :D

--------------------------------------------------------------------------------------------------------------
 
How to install: 
1. Put .jar into Plugins folder 
2. Reload server 
3. Edit the config.yml in plugins/MySqlVault and fill in your database information 
4. Reload server once again 
5. Enjoy! 

--------------------------------------------------------------------------------------------------------------

Commands: 
 /pay (Spielername) (Betrag) 
 /eco (set/give) (Spielername) (Betrag) 
 /money (Spielername) 
 
 --------------------------------------------------------------------------------------------------------------
 
Pay Befehl:
- Diesen Befehl können sowohl Spieler, als auch die Konsole nutzen, um anderen Spielern Geld zu überweisen

Permission: Keine

Eco Befehl:
  Give: 
  - Diesen Befehl können sowohl Spieler, als auch die Konsole nutzen, um anderen Spielern Geld hinzuzufügen
  Set: 
  - Diesen Befehl können sowohl Spieler, als auch die Konsole nutzen, um den Kontostand von anderen Spielern anzupassen
  
  Permission: "vault.eco.modify"
  
Money Befehl:
  Eigener Kontostand (/money):
  - Diesen Befehl können sowohl Spieler, als auch die Konsole nutzen, um den eigenen Kontostand einzusehen
  Fremder Kontostand (/money (Spielername)):
  - Diesen Befehl können sowohl Spieler, als auch die Konsole nutzen, um den Kontostand eines fremden Spielers einzusehen
  
Permission: "vault.money.others"

--------------------------------------------------------------------------------------------------------------
