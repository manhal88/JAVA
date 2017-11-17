package inf1010.assignment;

import java.util.Hashtable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Typical queries:
 *
 * http://127.0.0.1:8080/search?q=sdfsdfsdf
 * http://127.0.0.1:8080/calc?a=2&b=22
 *
 * Performance test harness:
 * ab -n 1000 -c 20 "http://127.0.0.1:8080/calc?a=4&b=100"
 *
 * Assume about 100 requests pr second
 */

@Controller
@ResponseBody
public class SuperCalculatorController {

    Hashtable<Integer,Integer> cache = new Hashtable<>();

    @RequestMapping("/calc")
    public Integer calc(@RequestParam("a") Integer p1, @RequestParam("b") Integer p2) throws InterruptedException {
        Calculation c = new Calculation(p1, p2);
        if (cache.contains(p1)) {
            return cache.get(p1);
        } else {
            JdbcUtil.doSomethingWeird();
            cache.put(p1, c.result());
            return c.result();
        }
    }

    static class Calculation {
        static int a;
        static int b;
        Calculation(Integer p1, Integer p2) {
            this.a = p1;
            this.b = p2;
        }

        int result() {
            return a * b;
        }

    }

    @RequestMapping("/search")
    public String search(@RequestParam("q") String q) {
        try {
            return JdbcUtil.query("select username, password, '" + q + "' as search_term from all_users where q like '%" + q + "%'");
        } catch (Exception e) {
        }
        return "Failed - goaway";
    }

}
