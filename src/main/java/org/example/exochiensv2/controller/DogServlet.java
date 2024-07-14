package org.example.exochiensv2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exochiensv2.model.Dog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "dogservlet", value = "/dog/*")
public class DogServlet extends HttpServlet {
    private List<Dog> dogs;
//    private DogService dogService;

    @Override
    public void init() throws ServletException {
        dogs = new ArrayList<Dog>();
//        dogService = new DogService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dogs", dogs);
        String pathInfo = req.getPathInfo().substring(1);
        int searchId;
        switch (pathInfo) {
            case "list":
                showAll(req, resp);
                break;
            case "add":
                showForm(req, resp);
                break;
            case "detail":
                showDetail(req, resp);
                break;
            default:
                System.out.println("pbm d'url");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
//j'ai retiré ces 2 lignes pour hibernate !
        Dog dog = new Dog(id, name, breed, dateOfBirth);
        dogs.add(dog);
//        dogService.create(name, breed, dateOfBirth);

        req.setAttribute("dogs", dogs);
//        req.setAttribute("dogs", dogService.findAll());
        req.getRequestDispatcher("/pages/list.jsp").forward(req, resp);
    }

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dogs", dogs);
//        req.setAttribute("dogs", dogService.findAll());
        req.getRequestDispatcher("/pages/list.jsp").forward(req, resp);
    }

    protected void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/form.jsp").forward(req, resp);
    }

    protected void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        Integer id = Integer.parseInt(idStr);
        Dog dog = findDogById(id);
//        Dog dog = dogService.findById(id);
        req.setAttribute("id", dog.getId());
        req.setAttribute("name", dog.getName());
        req.setAttribute("breed", dog.getBreed());
        req.setAttribute("dateOfBirth", dog.getDateOfBirth().toString());

        req.getRequestDispatcher("/pages/detail.jsp").forward(req, resp);

    }

    private Dog findDogById(int id) {
        for (Dog d : dogs) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
}