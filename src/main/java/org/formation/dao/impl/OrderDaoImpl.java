package org.formation.dao.impl;

import org.formation.dao.OrdersDao;
import org.formation.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderDaoImpl extends EntityDaoImpl<Order> implements OrdersDao {

}
