package com.example.Project2.web.admin;

import com.example.Project2.domain.Product;
import com.example.Project2.domain.User;
import com.example.Project2.service.Product.ProductService;
import com.example.Project2.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> allProduct = this.productService.getAllProduct();
        model.addAttribute("allProduct", allProduct);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String finishCreateProduct(Model model, @ModelAttribute Product product,
                                      @RequestParam("imageProductFile") MultipartFile file) {
        String finalFile = this.uploadService.handleSaveUploadService(file, "image");
        product.setImage(finalFile);

        this.productService.hanldeSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String finishUpdateProduct(Model model, @ModelAttribute Product product,
                                      @RequestParam("imageProductFile") MultipartFile file) {
        String finalFile = this.uploadService.handleSaveUploadService(file, "image");

        Product currentProduct = this.productService.getProductById(product.getId());
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDetailDesc(product.getDetailDesc());
        currentProduct.setShortDesc(product.getShortDesc());
        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setFactory(product.getFactory());
        currentProduct.setTarget(product.getTarget());
        if (!finalFile.isEmpty())
            currentProduct.setImage(finalFile);

        this.productService.hanldeSaveProduct(currentProduct);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/detail/{id}")
    public String getViewProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("product", currentProduct);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("product", currentProduct);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String finishCreateProduct(Model model, @ModelAttribute Product product) {
        this.productService.DeleteProductById(product.getId());
        return "redirect:/admin/product";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        this.productService.handleAddProductToCart(id, email, session);
        model.addAttribute("sum", session.getAttribute("sum"));
        return "redirect:/";
    }
}
