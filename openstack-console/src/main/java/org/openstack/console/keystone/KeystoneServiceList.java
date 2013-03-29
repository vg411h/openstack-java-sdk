package org.openstack.console.keystone;

import org.apache.commons.cli.CommandLine;
import org.openstack.console.Console;
import org.openstack.console.utils.Column;
import org.openstack.console.utils.Table;
import org.openstack.console.utils.TableModel;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.ListServices;
import org.openstack.keystone.model.Service;
import org.openstack.keystone.model.Services;

public class KeystoneServiceList extends KeystoneCommand {
	
	public KeystoneServiceList(KeystoneClient client) {
		super(client, "service-list");
	}

	@Override
	public void execute(Console console, CommandLine cmd) {
		
		final Services services = keystone.execute(new ListServices());
		
		Table t = new Table(new TableModel<Service>(services.getList()) {

			@Override
			public Column[] getHeaders() {
				return new Column[]{
					new Column("id", 32, Column.ALIGN_LEFT),
					new Column("type", 10, Column.ALIGN_LEFT),
					new Column("name", 10, Column.ALIGN_LEFT),
					new Column("description", 32, Column.ALIGN_LEFT)
				};
			}

			@Override
			public String[] getRow(Service service) {
				return new String[]{
					service.getId(),
					service.getType(),
					service.getName(),
					service.getDescription()
				};
			}
		});
		System.out.println(t.render());
	}

}
