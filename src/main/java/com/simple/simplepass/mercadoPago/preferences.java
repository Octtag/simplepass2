import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.exceptions.MPException;

// Configura tu credencial de acceso a la API de Mercado Pago
MercadoPago.SDK.setAccessToken("TU_ACCESS_TOKEN");

try {
    // Crea una nueva preferencia
    Preference preference = new Preference();

    // Configura los atributos de la preferencia
    preference.appendItem(new Item()
            .setTitle("Producto de ejemplo")
            .setQuantity(1)
            .setUnitPrice(100.0)
    );

    // Guarda la preferencia en la API de Mercado Pago
    preference.save();

    // Obtén el ID de la preferencia generada
    String preferenceId = preference.getId();

    System.out.println("Preferencia creada con ID: " + preferenceId);
} catch (MPException e) {
    // Manejo de excepciones
    e.printStackTrace();
}
