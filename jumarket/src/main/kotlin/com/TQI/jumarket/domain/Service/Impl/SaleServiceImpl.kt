package com.TQI.jumarket.domain.Service.Impl


import com.TQI.jumarket.domain.Service.SaleService
import com.TQI.jumarket.domain.data.model.Sale
import com.TQI.jumarket.domain.data.repositories.SaleRepository
import com.TQI.jumarket.domain.exceptions.BusinessRulesException
import com.TQI.jumarket.domain.exceptions.EntityNotFoundException
import com.TQI.jumarket.domain.exceptions.ErrorMessages
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaleServiceImpl(private val saleRepository: SaleRepository) : SaleService {
    override fun findAll(): List<Sale> {
        return saleRepository.findAll()
    }

    override fun findById(id: Long): Sale {
        val sale: Optional<Sale> = saleRepository.findById(id)
        return sale.orElseThrow { EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND) }
    }

    override fun create(model: Sale): Sale {
        if (saleRepository.existsById(model.id)) {
            throw BusinessRulesException(ErrorMessages.ID_ALREADY_EXISTS)
        }
        return saleRepository.save(model)
    }

    override fun update(id: Long, model: Sale): Sale {
        if (model.id != id || !saleRepository.findById(id).isPresent) {
            throw IllegalArgumentException(ErrorMessages.CANNOT_UPDATE_SALE)
        }
        val dbSale = this.findById(id)

        dbSale.cart = model.cart
        dbSale.paymentOptionEnum = model.paymentOptionEnum


        return saleRepository.save(dbSale)
    }

    override fun delete(id: Long) {
        if (saleRepository.findById(id).isPresent) {
            saleRepository.deleteById(id)
        } else {
            throw EntityNotFoundException(ErrorMessages.RECORD_NOT_FOUND)
        }
    }
}