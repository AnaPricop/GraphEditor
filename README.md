# GraphEditor
Am implementat urmatoarele functionalitati:
 - posibilitatea de a adauga un nod la nivelul cursorului
 - adaugarea unei muchii intre 2 noduri
 - posibilitatea de a sterge un nod

 - atunci cand adaugam un nod sau o muchie noua, putem alege culoarea si marimea acestora (la noduri, putem pune si text)
 - le putem muta oriunde pe canvas
 - am pus un exemplu de arbore (atunci cand se da click pe butonul Tree, apare pe canvas arborele exemplu)
 - se poate salva lucrarea atat ca si PDF, PNG cat si SVG
 - am implementat algoritmi care sa verifice daca graful este arbore/bipartit (cate un buton pentru fiecare)
 - afisarea listei de noduri sau muchii
 - am implementat un algoritm care sa creeze un graf random (apare o fereastra in care introducem numarul de noduri, astfel se alege o limita maxima de muchii ((n*(n-1))/2), pentru fiecare valoare necesara unui nod, am pus valori random, in functie de tipul valorii, dupa care m-am asigurat ca muchiile nu se repeta (de ex: 1-2, 2-1) pentru a putea folosi in continuare algoritmii de verificare (daca este arbore/bipartit)
 
 Am folosit in mare parte java SWING si java AWT event.
![Screenshot from 2021-06-02 08-32-23](https://user-images.githubusercontent.com/60388013/120428951-402dbe00-c37d-11eb-981f-c52f60589b7d.png)
