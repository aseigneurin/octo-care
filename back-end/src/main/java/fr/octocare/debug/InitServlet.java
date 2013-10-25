package fr.octocare.debug;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import fr.octocare.dataAccess.HexaDAO;
import fr.octocare.dataAccess.OctoDAO;
import fr.octocare.entity.Event;
import fr.octocare.entity.Hexa;
import fr.octocare.entity.Octo;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

    private final OctoDAO octoDAO = new OctoDAO();
    private final HexaDAO hexaDAO = new HexaDAO();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        {
            List<Octo> octos = octoDAO.getOctos();
            System.out.println("Before");
            for (Octo octo : octos)
                System.out.println(octo.getId() + " -> " + octo.getName());
        }
        {
            Hexa hexa1 = new Hexa();
            hexa1.setId(UUID.randomUUID().toString());
            hexa1.setEmail("Alexis.Seigneurin@gmail.com");
            hexa1.setName("Alexis S");

            Hexa hexa2 = new Hexa();
            hexa2.setId(UUID.randomUUID().toString());
            hexa2.setEmail("test@example.com");
            hexa2.setName("Test user");

            Octo octo1 = new Octo();
            octo1.setId("73408e49-d940-4de4-9798-c75ac4b09bd1");
            octo1.setName("Octo 1");

            Event e1 = new Event();
            e1.setText("Et si on allait...");
            e1.setDate(new Date(1338323623006l));
            e1.setOcto(Ref.create(octo1));
            e1.setAuthor(Ref.create(hexa1));

            Event e2 = new Event();
            e2.setText("Tiens, du lorem ipsum...");
            e2.setDate(new Date(1388323123006l));
            e2.setOcto(Ref.create(octo1));
            e2.setAuthor(Ref.create(hexa2));

            Event e3 = new Event();
            e3.setText("C'est chouette...");
            e3.setDate(new Date(1318323623816l));
            e3.setOcto(Ref.create(octo1));
            e3.setAuthor(Ref.create(hexa1));

            hexaDAO.saveHexa(hexa1);
            hexaDAO.saveHexa(hexa2);
            octoDAO.saveOcto(octo1);
            octoDAO.saveEvent(e1);
            octoDAO.saveEvent(e2);
            octoDAO.saveEvent(e3);
        }
        {
            List<Octo> octos = octoDAO.getOctos();
            System.out.println("After");
            for (Octo octo : octos)
                System.out.println(octo.getId() + " -> " + octo.getName());
        }

        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
