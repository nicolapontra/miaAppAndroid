package com.example.nicola.myapp.dummy;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.example.nicola.myapp.sensori.SensoreListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class SensoriContent {
        /**
     * An array of sample (dummy) items.
     */
    public static final List<DummySensore> SENSORI = new ArrayList<DummySensore>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummySensore> SENSORI_MAP = new HashMap<String, DummySensore>();
/* Per utilizzare getSystemService è richiesto un context relativo ad una activity, nel nostro caso SensoreListActivity
* Per tale motivo è necessario creare una istanza di tale activity nella stessa-nota sensoreListAct- e un metodo per
* ottenere tale istanza*/

    private static SensorManager mSensorManager = (SensorManager) SensoreListActivity.getListActivity().getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
    private static List<Sensor> sensoriDispositivo = mSensorManager.getSensorList(Sensor.TYPE_ALL);

    static {
        for (int i = 0; i < sensoriDispositivo.size(); i++) {
            addSensore(createDummySensore(i, sensoriDispositivo.get(i)));
        }

    }

    private static void addSensore(DummySensore item) {
        SENSORI.add(item);
        SENSORI_MAP.put(item.id, item);
    }

    private static DummySensore createDummySensore(int position, Sensor sensore) {
        return new DummySensore(String.valueOf(position), sensore.getName() , makeDetails(position,sensore));
    }

    private static String makeDetails(int position,Sensor sensore) {
        //costruire la stringa delle info: è utile StringBuilder ma si poteva usare anche String[]
        StringBuilder sensoreInfo = new StringBuilder();
        sensoreInfo.append("Name:" + sensore.getName()+ "\n")
                .append("Vendor:" + sensore.getVendor()+"\n")
                .append("Version:" + sensore.getVersion()+"\n")
                .append("Type:" + sensore.getType()+"\n")
                .append("Max range:" + sensore.getMaximumRange()+"\n")
                .append("Resolution:" + sensore.getResolution()+"\n")
                .append("Power:" + sensore.getPower()+"\n")
                .append("Min delay:" + sensore.getMinDelay());
        return sensoreInfo.toString();
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummySensore {
        public final String id;
        public final String content;
        public final String details;

        public DummySensore(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
