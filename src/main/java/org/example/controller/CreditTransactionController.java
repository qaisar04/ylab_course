package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.annotations.Loggable;
import org.example.dto.transaction.TransactionWithId;
import org.example.dto.transaction.TransactionWithoutId;
import org.example.exception.TransactionException;
import org.example.manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * The {@code CreditTransactionController} class is a Spring REST controller responsible for handling credit transaction
 * requests. It provides endpoints for making credit transactions, both with and without a transaction ID.
 *
 * <p>This controller is part of the API for credit transactions, allowing players to perform credit transactions.
 *
 * <p>Example usage:
 * <pre>
 * // Use this controller in your Spring application to handle credit transactions.
 * &lt;context:component-scan base-package="org.example.controller" /&gt;
 * </pre>
 *
 * The controller provides two main endpoints:
 * 1. `creditTransactionWithTransactionId` - Receives a credit transaction request with a transaction ID and returns
 *    a response, which can include an error message if the transaction fails.
 * 2. `creditTransactionWithoutTransactionId` - Receives a credit transaction request without a transaction ID and
 *    returns a response, which can include an error message if the transaction fails.
 *
 * Both endpoints are annotated with the `@Loggable` annotation for logging purposes.
 */
@RestController
@Api(value = "API for credit transaction", description = "this API allows you to make a credit transaction")
@RequestMapping(value = "/api/credit", produces = "application/json")
public class CreditTransactionController {

    private final PlayerManager playerManager;

    @Autowired
    public CreditTransactionController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Handles credit transactions with a transaction ID. Receives a credit transaction request and an authentication token,
     * then returns a response with the result of the transaction, which may include an error message in case of failure.
     *
     * @param transaction A {@code TransactionWithId} object containing transaction details, including a transaction ID.
     * @param token      An authentication token provided in the request header.
     * @return A ResponseEntity containing the result of the credit transaction, including an error message if the transaction fails.
     */
    @Loggable
    @ApiOperation(value = "post method for credit with transaction id", response = HashMap.class)
    @PostMapping("/id")
    public ResponseEntity<Map<String, String>> creditTransactionWithTransactionId(
            @RequestBody TransactionWithId transaction,
            @RequestHeader("Authorization") String token) {
            return playerManager.creditWithTransactionId(transaction, token);
    }

    /**
     * Handles credit transactions without a transaction ID. Receives a credit transaction request and an authentication token,
     * then returns a response with the result of the transaction, which may include an error message in case of failure.
     *
     * @param transaction A {@code TransactionWithoutId} object containing transaction details.
     * @param token      An authentication token provided in the request header.
     * @return A ResponseEntity containing the result of the credit transaction, including an error message if the transaction fails.
     */
    @Loggable
    @ApiOperation(value = "post method for credit without transaction id", response = HashMap.class)
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> creditTransactionWithoutTransactionId(
            @RequestBody TransactionWithoutId transaction,
            @RequestHeader("Authorization") String token) {
            return playerManager.creditWithoutTransactionId(transaction, token);
    }
}
