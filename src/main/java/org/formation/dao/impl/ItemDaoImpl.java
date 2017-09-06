package org.formation.dao.impl;


import org.formation.dao.ItemsDao;
import org.formation.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class ItemDaoImpl extends EntityDaoImpl<Item> implements ItemsDao {

	
}

