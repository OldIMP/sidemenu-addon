package org.vaadin.teemusa.sidemenu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.vaadin.teemusa.sidemenu.SideMenu.MenuRegistration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SideMenuTest {
    @Mock
    private SideMenu.MenuClickHandler clickHandler;

    @Test
    public void addTreeItemRootNotUserOriginated() {
        SideMenu sideMenu = new SideMenu();
        SideMenu.MenuRegistration item = sideMenu.addMenuItem("item",
                clickHandler);
        item.select();
        verify(clickHandler, times(1)).click();
    }

    @Test
    public void addTreeItemSubNotUserOriginated() {
        SideMenu sideMenu = new SideMenu();

        SideMenu.MenuRegistration item = sideMenu.addMenuItem("parent", null)
                .addSubMenu("item", clickHandler);
        item.select();
        verify(clickHandler, times(1)).click();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRootItemRepeat() {
        SideMenu sideMenu = new SideMenu();
        sideMenu.addMenuItem("item", clickHandler);
        sideMenu.addMenuItem("item", clickHandler);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSubItemRepeat() {
        SideMenu sideMenu = new SideMenu();
        MenuRegistration parent = sideMenu.addMenuItem("item", clickHandler);
        parent.addSubMenu("foo", null);
        parent.addSubMenu("foo", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTreeItemSubRepeat() {
        SideMenu sideMenu = new SideMenu();
        sideMenu.addMenuItem("parent1", clickHandler).addSubMenu("item",
                clickHandler);
        sideMenu.addMenuItem("parent2", clickHandler).addSubMenu("item",
                clickHandler);
    }
}
