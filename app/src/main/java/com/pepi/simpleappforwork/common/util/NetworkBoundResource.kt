package com.pepi.simpleappforwork.common.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    var data: ResultType? = null

    try {
        data = query().first()
    }
    catch (e: Throwable)
    {
        Resource.Error(e, data)
    }

    val flow = if (shouldFetch(data!!)) {  //this assertion is not good here it is just in this case because for sure data wont be null
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}