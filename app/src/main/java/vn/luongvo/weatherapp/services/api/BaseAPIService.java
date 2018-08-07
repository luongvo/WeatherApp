package vn.luongvo.weatherapp.services.api;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.luongvo.weatherapp.BuildConfig;

/**
 * Created by luongvo on 8/7/18.
 */
public abstract class BaseAPIService<T extends BaseAPIService, V> {

    private static final int TIMEOUT_CONNECTION = 30; // 30s

    private Retrofit retrofit;
    private V api;
    private Map<String, String> headers = new HashMap<>();

    /**
     * Headers that need to be added to every request can be specified using a RequestInterceptor
     */
    public BaseAPIService(Class<V> apiClass) {
        final SSLSocketFactory sslSocketFactory;
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Dangerous interceptor that rewrites the server's cache-control header.
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                // add global header here...

                return chain.proceed(builder.build());
            }
        };

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
                return verifier.verify(BuildConfig.HOST_NAME, sslSession);
            }
        };

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory)
                .addInterceptor(rewriteCacheControlInterceptor)
                .hostnameVerifier(hostnameVerifier);
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        // initialize RestAdapter
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonConverterBuilder.build()))
                .build();

        api = retrofit.create(apiClass);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * Get the API api to execute calls with
     */
    public V getApi() {
        return api;
    }

    @SuppressWarnings("unchecked")
    private T addHeader(String key, String value) {
        headers.put(key, value);
        return (T) this;
    }
}
