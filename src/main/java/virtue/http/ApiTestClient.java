package virtue.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuda
 * @date 2019/04/21
 */
public class ApiTestClient {

    private String scheme = "http";
    private String svcHost = "127.0.0.1";

    private int svcPort;

    public ApiTestClient() {
        this.svcPort = 8880;
    }

    public String getSvcHost() {
        return svcHost;
    }

    public void setSvcHost(String svcHost) {
        this.svcHost = svcHost;
    }

    public int getSvcPort() {
        return svcPort;
    }

    public void setSvcPort(int svcPort) {
        this.svcPort = svcPort;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> request(String resource, Map<String, Object> body) throws Exception {
        HttpPost httpRequest = new HttpPost(
                new URI(String.format("%s://%s:%d/%s",
                        scheme,
                        svcHost,
                        svcPort,
                        resource)));


        httpRequest.setHeader("Accept", ContentType.APPLICATION_JSON.toString());
        httpRequest.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());

        httpRequest.setEntity(new ByteArrayEntity(JsonFormatter.toJSONBytes(body), ContentType.APPLICATION_JSON));

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse httpResponse = httpClient.execute(httpRequest);

        return JsonFormatter.toObject(httpResponse.getEntity().getContent(), Map.class);

    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> reqBody = new HashMap<>();
//        reqBody.put("fatNum",11);
//        reqBody.put("fateDate","2019-04-21");
//        reqBody.put("fatCondition","超标");
//        reqBody.put("useId",1);
//        Map<String, Object> response = new ApiTestClient().request("/fat/add", reqBody);
////        reqBody.put("fatNum",11);
////        reqBody.put("fateDate","2019-04-21");
////        reqBody.put("fatCondition","超标1");
////        reqBody.put("useId",1);
//        Map<String, Object> response = new ApiTestClient().request("/fat/update", reqBody);
        reqBody.put("useId", 1);
        Map<String, Object> response = new ApiTestClient().request("/fat/query", reqBody);
        System.out.println(JsonFormatter.toPrettyJSON(response));
    }


}
