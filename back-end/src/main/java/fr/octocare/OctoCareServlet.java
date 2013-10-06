package fr.octocare;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import fr.octocare.dataAccess.OctoDAO;
import fr.octocare.entity.Event;
import fr.octocare.entity.Octo;

@SuppressWarnings("serial")
public class OctoCareServlet extends HttpServlet {

    private OctoDAO dao = new OctoDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        {
            List<Octo> octos = dao.getOctos();
            System.out.println("Before");
            for (Octo octo : octos)
                System.out.println(octo.getId() + " -> " + octo.getName());
        }
        {
            Octo octo1 = new Octo();
            octo1.setId("73408e49-d940-4de4-9798-c75ac4b09bd1");
            octo1.setName("Octo 1");

            Event e1 = new Event();
            e1.setText("Et si on allait...");
            e1.setDate(new Date(1338323623006l));
            e1.setOcto(Ref.create(octo1));

            Event e2 = new Event();
            e2.setText("Tiens, du lorem ipsum...");
            e2.setDate(new Date(1388323123006l));
            e2.setOcto(Ref.create(octo1));

            Event e3 = new Event();
            e3.setText("C'est chouette...");
            e3.setDate(new Date(1318323623816l));
            e3.setOcto(Ref.create(octo1));

            dao.saveOcto(octo1);
            dao.saveEvent(e1);
            dao.saveEvent(e2);
            dao.saveEvent(e3);
        }
        {
            List<Octo> octos = dao.getOctos();
            System.out.println("After");
            for (Octo octo : octos)
                System.out.println(octo.getId() + " -> " + octo.getName());
        }

        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
