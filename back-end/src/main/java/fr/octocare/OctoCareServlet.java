package fr.octocare;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.octocare.dataAccess.OctoDAO;
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
            dao.saveOcto(octo1);
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
