package test.business.validators;

import app.business.GetSession;
import app.business.exceptions.NegativeNumberException;
import app.business.exceptions.NotEnoughQuantityException;
import app.business.validators.GoodQuantity;
import app.orm.Good;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodQuantityTest {

    @Test
    void validQuantity() {
        List<Good> goods = initializeGoods();

        assertDoesNotThrow(() -> {
            new GoodQuantity(goods).validate();
        });

        deleteGoods(goods);
    }

    @Test
    void negativeQuantity() {
        List<Good> goods = initializeGoods();

        //Modifying one good to has invalid quantity
        goods.get(1).setQuantity(-5);

        assertThrows(NegativeNumberException.class, () -> {
            new GoodQuantity(goods).validate();
        });

        deleteGoods(goods);
    }

    @Test
    void zeroQuantity() {
        List<Good> goods = initializeGoods();

        goods.get(2).setQuantity(0);

        assertThrows(NegativeNumberException.class, () -> {
            new GoodQuantity(goods).validate();
        });

        deleteGoods(goods);
    }

    @Test
    void notEnoughQuantity() {
        List<Good> goods = initializeGoods();

        //Good3 has quantity of 20 so a value of 30 should throw exception
        goods.get(2).setQuantity(30);

        assertThrows(NotEnoughQuantityException.class, () -> {
            new GoodQuantity(goods).validate();
        });

        deleteGoods(goods);
    }

    //These goods are necessary just for the testing
    //After the tests they are destroyed
    List<Good> initializeGoods() {
        Session session = GetSession.getSession();

        //initialize goods
        Good good1 = new Good();
        good1.setGood("Test Good 1");
        good1.setQuantity(10);

        Good good2 = new Good();
        good2.setGood("Test Good 2");
        good2.setQuantity(5);

        Good good3 = new Good();
        good3.setGood("Test Good 3");
        good3.setQuantity(20);

        session.beginTransaction();
        session.save(good1);
        session.save(good2);
        session.save(good3);
        session.getTransaction().commit();

        List<Good> goods = new ArrayList<>();
        goods.add(good1);
        goods.add(good2);
        goods.add(good3);

        session.close();

        return goods;
    }

    //Destroying test goods
    void deleteGoods(List<Good> goods) {
        Session session = GetSession.getSession();

        session.beginTransaction();

        for(Good good : goods) {
            session.delete(good);
        }

        session.getTransaction().commit();
        session.close();
    }
}