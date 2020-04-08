#!/usr/bin/env bash

for num in {0..100}
do
    curl -d '{"name":"사용자 $num","money":1000}' \
    -H "Content-Type: application/json" \
    -X POST http://localhost:8080/api/cash
done

for num2 in {0..100}
do
    curl -d '{"cardName":"카드사 $num2","money":1000}' \
    -H "Content-Type: application/json" \
    -X POST http://localhost:8080/api/card
done