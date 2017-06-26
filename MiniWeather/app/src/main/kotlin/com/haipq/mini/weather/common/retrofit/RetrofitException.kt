package com.haipq.mini.weather.common.retrofit

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


/**
 * Created by haipq on 6/29/17.
 */
class RetrofitException(
        override val message: String?,
        val url: String?,
        val response: Response<*>?,
        val kind: Kind,
        val exception :Throwable?,
        val retrofit: Retrofit?) : RuntimeException(message, exception) {

    companion object {
        enum class Kind {
            /** An {@link IOException} occurred while communicating to the server. */
            NETWORK,
            /** A non-200 HTTP status code was received from the server. */
            HTTP,
            /**
             * An internal error occurred while attempting to execute a request. It is best practice to
             * re-throw this exception so your application crashes.
             */
            UNEXPECTED,

            OBJECT
        }


        fun httpError(url: String, response: Response<*>, retrofit: Retrofit?): RetrofitException {
            val message: String = "${response.code()} ${response.message()}"
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.UNEXPECTED, exception, null)
        }

        fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val httpException = throwable as HttpException
                val response = httpException.response()
                return RetrofitException.httpError(response.raw().request().url().toString(), response, null)
            }
            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.networkError(throwable)
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RetrofitException.unexpectedError(throwable)
        }

    }

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response == null || response.errorBody() == null) {
            return null
        }
        val converter: Converter<ResponseBody, T>? = retrofit?.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        return converter?.convert(response.errorBody())
    }
}