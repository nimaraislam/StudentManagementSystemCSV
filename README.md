Individuell inlämningsuppgift (JAVA)

Lösningsnamn: StudentManagementSystemCSV
Språk: Java
GUI-verktyg: Java Swing
Datalagring: CSV (Comma Separated Values) filer
Arkitektur: Enkel MVC (Model-View-Controller) struktur för bättre kodorganisation

Beskrivning av lösningen:
Superklass:
Person: Lagrar grundläggande personlig information såsom:
Förnamn och Efternamn
Personnummer
Födelsedatum (DOB)
Mobilnummer
E-post
Metod för att hämta information
Denna klass är grunden för andra klasser som Student och Lärare, vilka ärver dessa gemensamma detaljer.
Subklass:
Student: Lägger till studentens information i CSV-filen.
Teacher: Lägger till lärarens information i CSV-filen.
Andra klasser:
CsvFile: Hanterar CSV-filer för in- och utläsning (I/O).
Validation: Validerar fält innan data sparas till CSV-filen för Student- och Lärarklassen.
Course: Hanterar kurslistan från CSV-filen.
CourseGrade: Lägger till studentens betyg för varje kurs från kursfilen.
