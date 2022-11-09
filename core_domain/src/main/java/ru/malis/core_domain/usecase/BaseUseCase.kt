package ru.malis.core_domain.usecase

abstract class BaseUseCase<T> {

    abstract suspend operator fun invoke(): T
}