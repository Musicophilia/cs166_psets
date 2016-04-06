#!usr/bin/bash
printf "\n\n\n\n\n\n\n"
make
#java -ea RMQDriver rmq.PrecomputedRMQ 1
# java -ea RMQDriver rmq.SparseTableRMQ 0
java -ea RMQDriver rmq.HybridRMQ 0