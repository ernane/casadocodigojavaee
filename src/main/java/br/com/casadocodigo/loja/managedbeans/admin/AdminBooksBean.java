package br.com.casadocodigo.loja.managedbeans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.infra.MessagesHelper;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	private Book product = new Book();
	@Inject
	private BookDAO productDAO;
	private List<String> selectedAuthorsIds = new ArrayList<>();
	@Inject
	private MessagesHelper messagesHelper;

	@Transactional
	public String save() {
		populateBookAuthor();
		productDAO.save(product);
		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));
		clearObjects();
		return "/livros/lista?faces-redirect=true";
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map((strId) -> {
			return new Author(Integer.parseInt(strId));
		}).forEach(product::add);
	}

	public void setSelectedAuthorsIds(List<String> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public List<String> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public Book getProduct() {
		return product;
	}

}