package com.clip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdapterApplication

fun main(args: Array<String>) {
    runApplication<AdapterApplication>(*args)
}
