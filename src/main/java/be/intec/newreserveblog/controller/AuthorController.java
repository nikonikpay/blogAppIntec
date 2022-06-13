package be.intec.newreserveblog.controller;


import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.Role;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;
    private RoleService roleService;


    @Autowired
    public AuthorController(AuthorService authorService, RoleService roleService) {
        this.authorService = authorService;
        this.roleService = roleService;

    }

    @GetMapping("/")
    public String getAllAuthors(Model model) {
        return listByPage(1, "firstName", "asc", model);
    }

    @GetMapping("/page/{pageNumber}")

    public String listByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDirection") String sortDirection,
                             Model model) {

        Page<Author> pagedAuthors = authorService.getAllAuthors(currentPage, sortField, sortDirection);
        List<Author> authorsFromDb = pagedAuthors.getContent();
        long totalAuthors = pagedAuthors.getTotalElements();
        int totalPages = pagedAuthors.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalAuthors", totalAuthors);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("authors", authorsFromDb);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "user-list";
    }

    @GetMapping("/register")
    public String registerAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "register-form"; // placeholder
    }

    @PostMapping("/save")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("author", author);
            return "register-form";
        }
        authorService.createAuthorWithDefaultRole(author);
        return "redirect:/"; // placeholder
    }

    @GetMapping("/editRoles/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        Author authorToEdit = authorService.getAuthorById(id);
        List<Role> listOfRoles = authorService.getRoles();

        model.addAttribute("authorToEdit", authorToEdit);
        model.addAttribute("rolesList", listOfRoles);
        return "authority-form";
    }

    @PostMapping("/editRoles/save")
    public String giveRoleToAuthor(@ModelAttribute Author author, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("author", author);
            System.out.println("The problem is" + author);
            return "redirect:/authors/";
        }
        authorService.createAuthor(author);
        return "redirect:/authors/";
    }


    @GetMapping("/update")
    public String updateAuthorDataForm(@RequestParam Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        System.out.println(author);
        model.addAttribute("author", author);
        return "update-form";
    }

    /*
    Get mapping request to delete authors(users)
     */
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/authors/";
    }


}
