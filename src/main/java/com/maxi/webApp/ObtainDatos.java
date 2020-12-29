package com.maxi.webApp;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Service
public class ObtainDatos {
    private static String url = "https://cdn.buenosaires.gob.ar/datosabiertos/datasets/sbase/subte-viajes-molinetes/BaseUnificadaEstaciones.csv";
    private static HashMap<ArrayList<String>, Integer> hashMapTransitorio = new HashMap<>();
    private static List<Datos> listaDeDatos = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void obtainDatos() {

        hashMapTransitorio.clear();

        try {
            URL stockurl = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(stockurl.openStream()));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                ArrayList<String> arrayFechaLinea = new ArrayList<>();
                arrayFechaLinea.add(record.get(0));
                arrayFechaLinea.add(record.get("LINEA"));
                if (hashMapTransitorio.containsKey(arrayFechaLinea)){
                    int suma = hashMapTransitorio.get(arrayFechaLinea) + Integer.parseInt(record.get("CANTIDAD"));
                    hashMapTransitorio.put(arrayFechaLinea, suma);
                }else hashMapTransitorio.put(arrayFechaLinea, Integer.parseInt(record.get("CANTIDAD")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        listaDeDatos.clear();

        for (ArrayList<String> key: hashMapTransitorio.keySet()){
            Datos dato = new Datos();
            dato.setFecha(key.get(0));
            dato.setLinea(key.get(1).trim());
            dato.setPasajeros(hashMapTransitorio.get(key));
            listaDeDatos.add(dato);
        }
        Collections.sort(listaDeDatos, new Comparator<Datos>() {
            public int compare(Datos o1, Datos o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
    }

    public List<Datos> getAllDatos(){
        return listaDeDatos;
    }

    public String getDatosPorLinea(String linea){
        ArrayList<Datos> datosPorLinea = new ArrayList<>();
        for(Datos dato: listaDeDatos){
            if(dato.getLinea().equals(linea)) datosPorLinea.add(dato);
        }
        Gson gson = new Gson();
        String json = gson.toJson(datosPorLinea);
        return json;
    }




}