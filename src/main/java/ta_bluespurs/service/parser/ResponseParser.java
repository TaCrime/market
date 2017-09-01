package ta_bluespurs.service.parser;

import org.json.JSONObject;

import java.util.List;

public interface ResponseParser<U, T> {

    List<T> mapFirstItemsFromResponseToObjects(JSONObject response, U dataObject, int number);
}
