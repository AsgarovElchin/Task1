package com.example.task1.data

import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {
    val remote = remoteDataSource
}